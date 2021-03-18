package com.galactic.first.registry.model;



import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;



@Document(collection = "User")
public class User
{
    @Id
    @Indexed(unique = true)
    @ApiModelProperty(position = 0)
    private UUID id;

    @NotBlank(message = "user name is mandatory")
    @ApiModelProperty(position = 1)
    private String name;


    @NotBlank(message = "user name is mandatory")
    @ApiModelProperty(position = 2)
    private String username;

    @NotBlank(message = "user name is mandatory")
    @ApiModelProperty(position = 3)
    private String password;

    @NotBlank(message = "user name is mandatory")
    @ApiModelProperty(position = 4)
    private String email;

    @NotNull(message = "template disabled is mandatory")
    @ApiModelProperty(position = 5, example = "false", value = "Indicates if this master data has been disabled.")
    private Boolean disabled=false;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty(position = 6, example = "2021-02-25T15:46:13.824+00:00", value = "Field provided by server.")
    private Date modified;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty(position = 7, example = "2021-02-25T15:46:13.824+00:00", value = "Field provided by server.")
    private Date created;



    public User()
    {
    }

    public User(@NotBlank(message = "user name is mandatory") String name, @NotBlank(message = "user name is mandatory") String username, @NotBlank(message = "user name is mandatory") String password, @NotBlank(message = "user name is mandatory") String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void makeId(){
        this.id = UUID.randomUUID();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o )
        {
            return true;
        }
        if( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        User object = (User)o;
        return Objects.equals(this.id, object.id);
    }



    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }



    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    disabled: ").append(toIndentedString(disabled)).append("\n");
        sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
        sb.append("    created: ").append(toIndentedString(created)).append("\n");
        sb.append("}");
        return sb.toString();
    }



    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o )
    {
        if( o == null )
        {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }


}
