package test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "test")
public class Config implements Validator {

    private Map<String, Item> items;

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Config.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Config config = (Config) target;

        for (Map.Entry<String, Item> entry: config.getItems().entrySet()) {
            try {
                errors.pushNestedPath("items[" + entry.getKey() + "]");
                Item item = entry.getValue();
                item.validate(item, errors);
            } finally {
                errors.popNestedPath();
            }
        }

    }
}
