public class BaseURL {

    public static String propertyFile = "./Framework.properties";
    Utils util = new Utils();
    protected String intBaseURL;
    protected String qaBaseURL;



    protected String environment = util.getConfigPropertyValue(propertyFile, "ENV") ;

    public BaseURL() {
        if(environment.equals("QA")){
            qaBaseURL = util.getConfigPropertyValue(propertyFile, "QA_BASE_URL");
        } else {
            intBaseURL = util.getConfigPropertyValue(propertyFile, "INT_BASE_URL");
        }
    }

    public String getQaBaseURL() {
        return qaBaseURL;
    }
}
