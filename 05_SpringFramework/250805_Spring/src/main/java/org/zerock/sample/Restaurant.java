// Restaurant.java

package org.zerock.sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Setter;

@Component
@Data
@Setter
public class Restaurant {
    //@Setter(onMethod = @__({@Autowired}))
    //@Setter(onMethod_ = @Autowired)
    @Autowired
    private Chef chef;

}
