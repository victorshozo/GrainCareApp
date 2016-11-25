package usjt.graincare.models;

import com.google.gson.annotations.SerializedName;

public class EmailPasswordDTO {
    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
