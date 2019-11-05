package ar.com.ada.mongo.homeflix.repositories;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.mongo.homeflix.entities.*;

/**
 * UsuarioRepository
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
    Optional<Usuario> findBy_id(ObjectId _id);    
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
}