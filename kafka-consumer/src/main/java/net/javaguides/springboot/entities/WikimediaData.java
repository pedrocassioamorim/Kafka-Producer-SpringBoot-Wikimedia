package net.javaguides.springboot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wikimediarecentchange")
@Getter @Setter
public class WikimediaData {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String wikiEventData;

}
