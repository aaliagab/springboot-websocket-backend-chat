package springboot.websocketchat.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.websocketchat.models.dao.IChatRepository;
import springboot.websocketchat.models.documents.Mensaje;

@Service
public class ChatService implements IChatService{

	@Autowired
	IChatRepository chatRepository;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		// TODO Auto-generated method stub
		return chatRepository.findFirts10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		// TODO Auto-generated method stub
		return chatRepository.save(mensaje);
	}

}
