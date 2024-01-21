package es.juuangarciac.vitasnap;

import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import es.juuangarciac.vitasnap.user.security.AuthenticatedUser;
import es.juuangarciac.vitasnap.user.views.UserActivationView;
import es.juuangarciac.vitasnap.user.views.UserHomeView;
import es.juuangarciac.vitasnap.user.views.UserRegistrationView;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;


@PageTitle("Home")
@Route(value = "")
@AnonymousAllowed
public class LandingView extends VerticalLayout {

    @Value("${app.version}")
    private String appVersion;

    private final AuthenticatedUser authenticatedUser;

    public LandingView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.setHeightFull();
    }


    @PostConstruct
    public void init() {
        Div container = new Div();
        container.getStyle().set("position", "relative");

        H1 title = new H1("VitaSnap");
        title.getStyle().set("position", "absolute");
        title.getStyle().set("top", "50%");
        title.getStyle().set("left", "50%");

        Button button;
        if(!authenticatedUser.get().isPresent()) {
            button = new Button("Comienza ahora");
            button.addClickListener(e -> {
                UI.getCurrent().navigate("login");
            });
        } else {
            button = new Button("Tu perfil");
            button.addClickListener(e -> {
                UI.getCurrent().navigate("userhome");
            });
        }
        button.getStyle().set("position", "absolute");
        button.getStyle().set("bottom", "50%");
        button.getStyle().set("right", "50%");

        container.add(createCarousel(), title, button);
        add(container);

    }

    public Carousel createCarousel() {
        //slide: 1
        Slide slide1 = new Slide();
        StreamResource imageResource1 = new StreamResource("imageSlide1",
                () -> getClass().getResourceAsStream("/images/img_A1.jpg"));

        Image image1 = new Image(imageResource1, "Adventure");
            image1.setWidth("100%");
            image1.setHeight("100%");
            image1.getStyle().set("object-fit", "contain"); // Ajusta la imagen para que se mantenga su relación de aspecto
        slide1.add(image1);

        //slide: 2
        Slide slide2 = new Slide();
        StreamResource imageResource2 = new StreamResource("imageSlide2",
                () -> getClass().getResourceAsStream("/images/img_A2.jpeg"));

        Image image2 = new Image(imageResource2, "Adventure");
            image2.setWidth("100%");
            image2.setHeight("100%");
            image2.getStyle().set("object-fit", "contain");
        slide2.add(image2);

        //slide: 3
        Carousel carousel = new Carousel(slide1, slide2)
        .withAutoProgress()
        .withoutSwipe()
        .withSlideDuration(4)
        .withStartPosition(0);
        
        carousel.setWidth("60%");
        carousel.setHeight("60%");

        return carousel;
    }
}
