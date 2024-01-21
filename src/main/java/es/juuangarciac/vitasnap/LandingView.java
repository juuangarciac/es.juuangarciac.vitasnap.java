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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
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
        container.add(createCarousel());
        
        H1 title = new H1("VitaSnap");
        title.getStyle().set("position", "relative");
        title.getStyle().set("z-index", "1");
        
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
        
        button.getStyle().set("position", "relative");
        button.getStyle().set("z-index", "2");
        
        Div textDiv = new Div();
            textDiv.getStyle().set("position", "absolute");
            textDiv.getStyle().set("top", "65%");
            textDiv.getStyle().set("right", "5%");
            textDiv.getStyle().set("background", "white"); // Añade un fondo blanco al recuadro
            textDiv.getStyle().set("border-radius", "15px"); // Añade bordes redondeados al recuadro
            textDiv.getStyle().set("border", "2px solid #D3D3D3"); // Añade un borde gris claro al recuadro
            textDiv.getStyle().set("padding", "15px"); // Añade un poco de espacio alrededor del texto y el botón
        textDiv.add(title, button);
    
        add(container, textDiv);
    }
    
    

    public Carousel createCarousel() {
        //slide: 1
        Slide slide1 = new Slide();
        StreamResource imageResource1 = new StreamResource("imageSlide1",
                () -> getClass().getResourceAsStream("/images/img_A1.jpg"));

        Image image1 = new Image(imageResource1, "Adventure");
            image1.setWidthFull();
            image1.setHeightFull();
            image1.getStyle().set("object-fit", "contain"); // Ajusta la imagen para que se mantenga su relación de aspecto
        slide1.add(image1);

        //slide: 2
        Slide slide2 = new Slide();
        StreamResource imageResource2 = new StreamResource("imageSlide2",
                () -> getClass().getResourceAsStream("/images/img_A2.jpeg"));

        Image image2 = new Image(imageResource2, "Adventure");
            image2.setWidthFull();
            image2.setHeightFull();
            image2.getStyle().set("object-fit", "contain");
        slide2.add(image2);

        Carousel carousel = new Carousel(slide1, slide2);
        carousel.setSizeFull();
        
        return carousel;
    }
    
}
