package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class LoginValidator implements IFieldValidator {

    @Override
    public String getFieldName() {
        return "Login";
    }

}
