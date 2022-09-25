package springboot.websocketchat.models.services;

import java.util.List;

import springboot.websocketchat.models.documents.Mensaje;

public interface IChatService {
	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar(Mensaje mensaje);
}
