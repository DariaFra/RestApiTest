package lesson4;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddToShoppingListResponse {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
}
