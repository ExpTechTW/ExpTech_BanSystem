package handler;

public class decode {
    public static String Decode(Integer response) {
        String ans = null;
        if(response==0){
            ans="\u60e1\u610f\u639b\u6a5f";
        }else if(response==1){
            ans="\u8a00\u8a9e\u8fb1\u7f75";
        }else if(response==2){
            ans="\u60e1\u610f\u4f7f\u7528BUG";
        }else if(response==3){
            ans="\u5077\u7aca\u4ed6\u4eba\u8ca1\u7269";
        }else if(response==4){
            ans="\u7834\u58de\u4ed6\u4eba\u4f5c\u54c1";
        }else if(response==5){
            ans="\u5916\u639b-\u52d5\u614b\u985e";
        }else if(response==6){
            ans="\u5916\u639b-\u6e32\u67d3\u985e";
        }
        return ans;
    }
}
