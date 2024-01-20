package es.juuangarciac.vitasnap;

import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;

import com.vaadin.flow.component.html.H3;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.server.StreamResource;

import com.vaadin.flow.server.auth.AnonymousAllowed;

import jakarta.annotation.PostConstruct;
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
        // Slide : 1
        Slide slide1 = new Slide();
        StreamResource imageResource = new StreamResource("image",
        () -> getClass().getResourceAsStream("images/img_1.jpg"));
        Image image = new Image(imageResource, "Tres personas haciendo surf al atardecer");
            image.setHeight("553.5px");
            image.setWidth("368px");
        slide1.add(image);

        // Slide : 2
        /*TO-DO:
            Slide slide2 = new Slide();
            imageResource = new StreamResource("image",
            () -> getClass().getResourceAsStream("images/img_2.jpg"));
            image = new Image(imageResource, "Gente Feliz de fiesta");
                image.setHeight("553.5px");
                image.setWidth("368px");
            slide2.add(image);
        */

        Carousel carousel = new Carousel(slide1)
            .withAutoProgress()
            .withoutSwipe()
            .withSlideDuration(4)
            .withStartPosition(0);

        return carousel;
    }

}
