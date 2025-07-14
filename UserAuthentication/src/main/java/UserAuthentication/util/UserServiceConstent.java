package UserAuthentication.util;

public interface UserServiceConstent {

    String API = "/api";       
    String V1 = API + "/v1";
    String USER =  V1 + "/user";

    String REGISTER  = "/register";
    String LOGIN = "/login";
    String GETUSER = "/{id}";
    String GETALLUSERS = "getallusers";

}


//        It's a constant (cannot be changed)
//         It's globally accessible (via UserServiceConstent.API)
//         You don’t need to create an object to use it