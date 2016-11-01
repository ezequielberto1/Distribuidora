package negocio;

import java.util.ArrayList;

import utils.ApplicationException;
import entidades.Cliente;

public class CtrlABMVentas {
private ArrayList<Cliente> clientes;
	
	public CtrlABMCCliente(){
		clientes = new ArrayList<Cliente>();
	}
	
	public void add(Cliente c) throws ApplicationException {
		if(!clientes.contains(c)){
			clientes.add(c);
		} else {
			throw new ApplicationException("El cliente ya existe");
		}
	}
	
	public void update(Cliente c) throws ApplicationException{
		if(clientes.contains(c)){
			Cliente cliEnc=this.getCliente(c);
			cliEnc.setApellido(c.getApellido());
			cliEnc.setNombre(c.getNombre());
			cliEnc.setRazon_social(c.getRazon_social());
			cliEnc.setTelefono(c.getTelefono());
		}else{
			throw new ApplicationException("El cliente no existe");
		}
		
	}
	
	public void delete(Cliente c){
		clientes.remove(c);
	}
	
	public Cliente getCliente(Cliente c){
		Cliente cliEnc=null;
		int i=clientes.indexOf(c);
		if(i>=0){
			cliEnc=clientes.get(i);
		}
		return cliEnc;
	}

}
