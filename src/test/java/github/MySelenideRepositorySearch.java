package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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
        //assert that pages contain SoftAssertions and open it
        SelenideElement wikiRightbar = $(".wiki-rightbar");
        wikiRightbar.$(byText("Show 2 more pages…")).click();
        wikiRightbar.$(byText("SoftAssertions")).click();
        //assert that SoftAssertions contain JUnit code example

        //by sibling()
        $("#wiki-content .markdown-body")
                .$(byText("3. Using JUnit5 extend test class:"))
                .sibling(0)
                .shouldHave(text("class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));

        //by $$().findBy()
        $("#wiki-content .markdown-body")
                .$$("div pre")
                .findBy(text("@ExtendWith({SoftAssertsExtension.class})"))
                .shouldHave(text("class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));
    }
}
