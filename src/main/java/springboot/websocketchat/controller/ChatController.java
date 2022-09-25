package springboot.websocketchat.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import springboot.websocketchat.models.documents.Mensaje;
import springboot.websocketchat.models.services.IChatService;

@Controller
public class ChatController {
	
	@Autowired
	IChatService chatService;
	
	@Autowired
	SimpMessagingTemplate webSocket;

	private String[] colores = {"red", "green", "blue", "black", "DeepPink", 
			"OrangeRed", "Gold", "MediumOrchid", "LimeGreen",
			"magenta", "purple", "orange"};
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());		
		if(mensaje.getTipo().equals("MENSAJE")) {
			mensaje.setTexto(mensaje.getTexto());
			chatService.guardar(mensaje);
		}
		else if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[(int)(Math.random()*12)]);
			mensaje.setTexto("El usuario "+mensaje.getUsername().toUpperCase()+" se ha conectado");
		}			
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String escribiendoMensaje(String username) {
		return username.concat(" está escribiendo...");
	}
	
	@MessageMapping("/hitorial")
	@SendTo("/chat/historial")
	public void historialMensajes(String clienteId) {
		webSocket.convertAndSend("/chat/historial/"+clienteId,chatService.obtenerUltimos10Mensajes());
	}
}
