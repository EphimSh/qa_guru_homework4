package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MySelenideRepositorySearch {

    @BeforeAll
    static void init() {
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideWikiSoftAssertionsPageShouldHaveJUnit5CodeExamples() {
        open("https://github.com");

        $("[placeholder='Search GitHub']").setValue("selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();

        // goto selenide wiki page
        $("ul.UnderlineNav-body").$("#wiki-tab").click();
        //goto SoftAssertions
        $(byText("Soft assertions")).click();
        
        //assert that SoftAssertions contain JUnit code example
        //by sibling()
        $("#wiki-content .markdown-body")
                .$(byText("3. Using JUnit5 extend test class:"))
                .sibling(0)
                .shouldHave(text("@Test"));
        //by $$().findBy()
        $("#wiki-content .markdown-body")
                .$$("div pre")
                .findBy(text("@ExtendWith({SoftAssertsExtension.class})"))
                .shouldHave(text("@Test"));
    }
}
