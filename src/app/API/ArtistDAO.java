
package app.API;

import DTO.ArtistDTO;

import java.io.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.select.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class ArtistDAO {

    public ArrayList<ArtistDTO> docArtistInfo(String inputSearch) {

        String queryInput = strToQuery(inputSearch);
        String searchUrl = "https://www.nhaccuatui.com/tim-kiem?q=" + queryInput + "&b=singer";
        ArrayList<ArtistDTO> artistInfos = null;
        
        
        try {
            // ================= Xử lý bước SEARCH để return link thông tin ca sĩ =========================
            Document docSearch = Jsoup.connect(searchUrl).method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute()
                    .parse();
            Elements checkError = docSearch.select(".sn_title_info_key");
            ArtistDTO tempDTO = new ArtistDTO();
             
            if (checkError.text().contains("Không có")) {
                artistInfos = new ArrayList<>();
                tempDTO.setImage("");
                tempDTO.setNameArtist("");
                tempDTO.setBirthday("");
                tempDTO.setGender("");
                tempDTO.setCountry("");
                tempDTO.setInfolife("");
                tempDTO.setAlbum("");
                artistInfos.add(tempDTO);
                return artistInfos;
            }
            //trả về 5 link thông tin ca sĩ gần đúng với input
            Elements hrefSongofInput = docSearch.select(".sn_search_single_song .singer_song a");
            ArrayList<String> linksArtist = new ArrayList<String>();

            //thêm vào arraylist
            for (Element x : hrefSongofInput) {
                String temp = x.attr("href");
                if (!temp.contains("?")) {
                    linksArtist.add(temp);
                    break;
                }
            }
            
            if(linksArtist.size() == 0){
                artistInfos = new ArrayList<>();
                tempDTO.setImage("");
                tempDTO.setNameArtist("");
                tempDTO.setBirthday("");
                tempDTO.setGender("");
                tempDTO.setCountry("");
                tempDTO.setInfolife("");
                tempDTO.setAlbum("");
                artistInfos.add(tempDTO);
                return artistInfos;
            }

            //in ra kết quả (test)
//            for (int i = 0; i < linksArtist.size(); i++) {
//                System.out.println(linksArtist.get(i));
//            }

            //================= Xử lý bước LẤY THÔNG TIN ca sĩ từ link trên trả về =========================
            //lưu tất cả mọi thứ trong đây
            ArrayList<ArrayList<String>> artistBIGInfos = new ArrayList<ArrayList<String>>();

            Document docArtist;
            //vòng lặp để chạy hết các link ở trên trả về
            ArtistDTO artist = new ArtistDTO();
            for (int i = 0; i < linksArtist.size(); i++) {
                docArtist = Jsoup.connect(linksArtist.get(i)).method(Connection.Method.GET)
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .execute()
                        .parse();

                Elements eleInfo = docArtist.select(".singer-left-avatar *");
                artistInfos = new ArrayList<>();

                //xử lý dữ liệu eleInfo để add vào arraylist theo dạng String
                ArrayList<String> tempArr = new ArrayList<String>();
                int count = 0;
                for (Element x : eleInfo) {
                    String temp;
                    if (x.toString().contains("src")) {
                        temp = x.attr("src");
                    } else {
                        temp = x.text();
                    }
                    tempArr.add(temp);
                    count++;
                    
                }
                
                tempArr.remove(0); //sau bước này hoàn thành việc lấy thông tin ca sĩ (không có tiểu sử)
                if(count == 5){
                    tempArr.add(1, "Không tiết lộ");
                }
                //lấy tiểu sử
                Elements eleDesc = docArtist.select("#divDescription");
                tempArr.add(eleDesc.text());

                //lấy tên các album
                Elements eleAlbumNames = docArtist.select(".listAlbumName li .info_album h3");
                String StrAlbumNames = "";
                for (Element x : eleAlbumNames) {
                    StrAlbumNames += x.text() + ";";
                }
                tempArr.add(StrAlbumNames);

                //xuat ket qua
                
                artist.setImage(tempArr.get(0));
                artist.setNameArtist(tempArr.get(1));
                artist.setBirthday(tempArr.get(2));
                artist.setGender(tempArr.get(3));
                artist.setCountry(tempArr.get(4));
                artist.setInfolife(tempArr.get(5));
                artist.setAlbum(tempArr.get(6));
                artistInfos.add(artist);

            }

        } catch (IOException e) {
            return null;
        }
        return artistInfos;
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

}
