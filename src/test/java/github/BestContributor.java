package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BestContributor {


    @BeforeAll
    static void init(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void andrewSolntsevShouldBeBestContributor(){
        open("https://github.com/selenide/selenide");
        //move mouse to avatar image of contributors block
        $("div.Layout-sidebar").$(byText("Contributors"))
                .closest(".BorderGrid-cell").$$("ul li").first().hover();
        //assert
        $$(".Popover").findBy(visible).shouldHave(text("Andrei Solntsev"));
    }
}
