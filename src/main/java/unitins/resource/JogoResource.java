package unitins.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import unitins.model.Jogo;

@Path("/jogo")
public class JogoResource {
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Jogo> getAll(){

        return Jogo.findAll().list();
    }
    @GET
    @Path("/{id}")
    public Jogo get(@PathParam ("id") Long id) {

        Jogo jogo = Jogo.findById(id);

        if (jogo.isPersistent())        
            return Jogo.findById(id);

        return null;
    }

    @POST // fazer uma inclusão
    @Produces(MediaType.APPLICATION_JSON) //
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Jogo insert(Jogo jogo) {
        jogo.persist();
        return jogo;
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void delete(@PathParam ("id") Long id) {

        Jogo jogo = Jogo.findById(id);

        if (jogo.isPersistent())
            jogo.delete();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Jogo update (@PathParam ("id") Long id, Jogo jogo) {

        Jogo entity = Jogo.findById(id);

        entity.setNome(jogo.getNome());

        entity.setGenero(jogo.getGenero());

        entity.setPlataforma(jogo.getPlataforma());

        entity.setAnoLancamento(jogo.getAnoLancamento());

        entity.setPreco(jogo.getPreco());

        return entity;
    }

}

