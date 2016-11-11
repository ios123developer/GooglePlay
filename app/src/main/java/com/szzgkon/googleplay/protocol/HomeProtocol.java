package com.szzgkon.googleplay.protocol;


public class HomeProtocol {

    public void load(int index){
        //1.请求服务器

        String json = loadLocal(index);
                if(json == null) {
                    json = loadServer();


                    json = loadServer();

                    if (json != null) {
                        saveLocal(json, index);
                    }

         }
        if(json != null){
            paserJson(json);
        }

    }
    private String loadLocal(int index) {
        return null;
    }
    private String loadServer() {
        return null;
    }
    private void saveLocal(String json, int index) {

    }
    private void paserJson(String json) {

    }






}
