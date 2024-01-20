package es.juuangarciac.vitasnap;

import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Vertical;

import es.juuangarciac.vitasnap.user.views.UserActivationView;
import es.juuangarciac.vitasnap.user.views.UserHomeView;
import es.juuangarciac.vitasnap.user.views.UserRegistrationView;
import jakarta.annotation.PostConstruct;

import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Value;

@PageTitle("Home")
@Route(value = "")
@AnonymousAllowed
public class LandingView extends VerticalLayout {

    @Value("${app.version}")
    private String appVersion;


    public LandingView() {
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.setHeightFull();
    }


    @PostConstruct
    public void init() {
        // Este método se ejecuta después de que se ejecute el constructor y se inyecten las dependencias (appVersion)
        
        VerticalLayout verticalLayout = new VerticalLayout(new H1("VitaSnap"), new H3("Momentos inolvidables. Para gente inolvidable"));
            verticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout(verticalLayout, createCarousel());
        
        add(horizontalLayout);
    }

    public Carousel createCarousel() {
        Slide slide1 = new Slide();
        Image img = new Image("frontend/img/home/img_1.jpg", "Tres personas haciendo surf al atardecer");
        slide1.add(img);

        Carousel carousel = new Carousel(slide1)
        .withAutoProgress()
        .withoutSwipe()
        .withSlideDuration(4)
        .withStartPosition(0);

        return carousel;
    }

}
