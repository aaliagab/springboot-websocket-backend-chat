package springboot.websocketchat.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import springboot.websocketchat.models.documents.Mensaje;

public interface IChatRepository extends MongoRepository<Mensaje, String>{
	public List<Mensaje> findFirts10ByOrderByFechaDesc();
}
