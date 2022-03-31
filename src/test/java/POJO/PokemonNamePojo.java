package POJO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PokemonNamePojo {
   private int count;
   private String next;
   private String previous;
   private List<PokemonTypePojo> results;



}
