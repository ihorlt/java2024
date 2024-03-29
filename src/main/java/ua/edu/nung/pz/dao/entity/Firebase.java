package ua.edu.nung.pz.dao.entity;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * URL https://firebase.google.com/docs/admin/setup
 */
public class Firebase {
    public static final String USER_EXISTS = "User exists";
    public static final String PASSWORD_OK = "PASSWORD_OK";
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private String firebaseConfigPath;
    private String firebaseName;
    private String apiKey;
    private String signInUrl;
    private static Firebase firebase = new Firebase();
    private Firebase() {
    }

    public static Firebase getInstance() {
        return firebase;
    }

    public void init() {
        FileInputStream refreshToken = null;
        try {
            refreshToken = new FileInputStream(firebaseConfigPath);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl(firebaseName)
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String createUser(User user) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setEmailVerified(false)
                .setPassword(user.getPassword())
                .setDisplayName(user.getDisplayName())
                .setDisabled(false);

        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().createUser(request);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        return "Successfully created new user: " + userRecord.getUid();
    }

    public String getUserByEmail(String email) {
        UserRecord userRecord;
        String msg = USER_EXISTS;
        try {
            userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            System.out.println("userRecord " + userRecord);
        } catch (FirebaseAuthException e) {
            msg = e.getAuthErrorCode().toString();
        }
        return msg;
    }

    public String signInWithEmailAndPassword(String email, String password) {
        final String signInUrlLoc = signInUrl + apiKey;

        try {
            URL url = new URL(signInUrlLoc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JsonObject jsonRequest = new JsonObject();
            jsonRequest.addProperty("email", email);
            jsonRequest.addProperty("password", password);
            jsonRequest.addProperty("returnSecureToken", true);

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(jsonRequest.toString().getBytes());
            }

            StringBuilder response;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                response = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            if (jsonResponse.has("idToken")) {
                return PASSWORD_OK;
            } else {
                String errorMessage = jsonResponse.get("error").getAsJsonObject().get("message").getAsString();
                return errorMessage;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void setFirebaseConfigPath(String firebaseConfigPath) {
        this.firebaseConfigPath = firebaseConfigPath;
    }

    public void setFirebaseName(String firebaseName) {
        this.firebaseName = firebaseName;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSignInUrl(String signInUrl) {
        this.signInUrl = signInUrl;
    }
}
