package lesson4;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder( {
            "item",
            "aisle",
            "parse"
    })
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class AddToShoppingListRequest {
        @JsonProperty("item")
        private String item;
        @JsonProperty("aisle")
        private String aisle;
        @JsonProperty("parse")
        private boolean parse;
    }
