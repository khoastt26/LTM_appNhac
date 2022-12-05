/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app.API;


import DTO.SongDTO;
import static app.helper.ReplaceBetween.*;
import static app.helper.getAPI.*;

import java.io.*;
import java.util.*;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class SongDAO {

    /**
     * @param args the command line arguments
     */
    public ArrayList<SongDTO> docSongInfo(String inputSearch) {
        ArrayList<SongDTO> songInfos = new ArrayList<>();
        //INFO API
        String musicSearchApi = "https://ac.zingmp3.vn/v1/web/featured?query=";
//        Scanner sc = new Scanner(System.in);
//        = sc.nextLine();

        try {
            //song info
            Document docMusicSearch = Jsoup.connect(musicSearchApi + strToQuery(inputSearch))
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute()
                    .parse();

            // ========================= JSON ==================================
            //Lấy json danh sách bài hát

            JSONObject joInfo = new JSONObject(docMusicSearch.text());
            JSONObject joInfoData = new JSONObject(joInfo.get("data").toString());
            JSONArray joInfoItems = new JSONArray(joInfoData.get("items").toString());
            
            SongDTO tempDTO = new SongDTO();
             
            if (joInfoItems.isEmpty()) {
                songInfos = new ArrayList<>();
                tempDTO.setIconName("");
                tempDTO.setArtist("");
                tempDTO.setLyrics("");
                tempDTO.setStream("");
                tempDTO.setTitle("");                
                songInfos.add(tempDTO);
                return songInfos;
            }
            
            JSONObject joInfoItems2 = null;
            for (int i = 0; i < joInfoItems.length(); i++) {
                JSONObject temp = new JSONObject(joInfoItems.get(i).toString());
                if (Integer.parseInt(temp.get("type").toString()) == 9) {
                    joInfoItems2 = new JSONObject(joInfoItems.get(i).toString());
                    break;
                }
            }

            //Lấy được array thông tin của những bài hát theo input search
            // lấy được: id, tên bài hát, thumbnail bài hát
            JSONArray joInfoList = new JSONArray(joInfoItems2.get("items").toString());

            JSONObject joInfoSong;

            //lưu lại hết thông tin của tất cả các bài hát
            for (int i = 1; i < joInfoList.length(); i++) {
                //lưu thông tin cho từng bài hát                

                //lấy json từng bài hát của api Feature
                joInfoSong = new JSONObject(joInfoList.get(i).toString());

                // Title, id, thumb
                int check = Integer.parseInt(joInfoSong.get("type").toString());
                if (check != 1) {
                    continue;
                }
                
                String songTitle = joInfoSong.get("title").toString();
                String songId = joInfoSong.get("id").toString();
                String songThumb = joInfoSong.get("thumb").toString();
                JSONArray artist = joInfoSong.getJSONArray("artists");
                String nameArtist = "";

//                for (int j = 0; j < artist.length(); j++) {

                    nameArtist += artist.getJSONObject(0).get("name");
//                }
                //lấy json thông tin của bài hát theo songId của api get-song-info

                String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0";
                Connection.Response resGetSongInfo = Jsoup.connect(getApiById(songId)).method(Connection.Method.GET)
                        .ignoreContentType(true).ignoreHttpErrors(true)
                        .header("Connection", "keep-alive")
                        .timeout(5000)
                        .execute();

                resGetSongInfo = Jsoup.connect(getApiById(songId))
                        .userAgent(userAgent)
                        .cookies(resGetSongInfo.cookies())
                        .method(Connection.Method.GET)
                        .timeout(5000)
                        .cookies(resGetSongInfo.cookies())
                        .ignoreContentType(true).ignoreHttpErrors(true)
                        .execute();

                Document apiGetSongInfo = resGetSongInfo.parse();

                String temp = replaceBetweenWithoutRegex(apiGetSongInfo.text(),
                        "\"artists\":[", "],",
                        true, true, "");
                temp = replaceBetweenWithoutRegex(temp,
                        "\"listen\":", ",",
                        true, true, "");

                JSONObject joGetSongInfo = new JSONObject(temp);
                int checkErr = Integer.parseInt(joGetSongInfo.get("err").toString());
                if (checkErr == 0) {
                    JSONObject joGetSongData = new JSONObject(joGetSongInfo.get("data").toString());

                    //json lyric
                    JSONObject joGetSongStream = new JSONObject(joGetSongData.get("streaming").toString());
                    JSONObject joGetSongDefault = new JSONObject(joGetSongStream.get("default").toString());

                    //url lyric.lrc, url streaming
                    String songLyric = joGetSongData.getString("lyric");
                    String songStream = joGetSongDefault.getString("128");

                    //add vô songInfos
                    SongDTO song = new SongDTO();
                    song.setTitle(songTitle);
                    song.setArtist(nameArtist);
                    song.setIconName(songThumb);
                    song.setLyrics(songLyric);
                    song.setStream(songStream);
                    songInfos.add(song);
                }

                //PRINT
//                System.out.println("Thông tin bài hát: ");
//                for(int j =0; j < songInfos.size(); j++){
//                    System.out.println(j + ": " + songInfos.get(j));
//                }
//                System.out.println("");
                //add vô allSongInfos                
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return songInfos;

    }

    public static String strToQuery(String input) {

        String result = "";
        StringTokenizer st = new StringTokenizer(input, " ", true);

        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            if (temp.equals(" ")) {
                result += "%20";
            } else {
                result += temp;
            }
        }

        return result;
    }

    public static String getLyricBylrc(String fileLyric) {
        try {
            URL url = new URL(fileLyric);
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String i;
            String store = "";
            while ((i = read.readLine()) != null) {
                i = replaceBetweenWithoutRegex(i, "[", "]", true, true, "");
                store += i + "\n";
            }
            read.close();
            return store;
        } catch (IOException e) {
            return "";
        }
    }

}
