import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfabankTest {


    @BeforeAll
    static void init(){
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void testAlfa(){
        open("https://alfabank.ru");
        $(byText("Вклады")).click();
        $("body").shouldHave(text("Альфа-Вклад"));
        $("#alfa-deposit").$(byText("Открыть")).click();
        $("body").shouldHave(text("Обратная связь"));
    }
}
