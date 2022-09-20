package springboot.websocketchat.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import springboot.websocketchat.models.documents.Mensaje;

@Controller
public class ChatController {

	private String[] colores = {"red", "green", "blue", "black", "DeepPink", 
			"OrangeRed", "Gold", "MediumOrchid", "LimeGreen",
			"magenta", "purple", "orange"};
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		mensaje.setColor(colores[(int)(Math.random()*12)]);
		if(mensaje.getTipo().equals("MENSAJE"))
			mensaje.setTexto(mensaje.getTexto());
		else if(mensaje.getTipo().equals("NUEVO_USUARIO"))
			mensaje.setTexto("El usuario "+mensaje.getUsername().toUpperCase()+" se ha conectado");
		return mensaje;
	}
}
