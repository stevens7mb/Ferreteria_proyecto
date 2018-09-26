/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import CAD.UsuarioCad;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import DTO.usuario;

import org.primefaces.event.SelectEvent;


/**
 *
 * @author JuaNFeR
 */
@ManagedBean
@Named(value = "usuario")
@SessionScoped
public class usuarioController implements Serializable {

    
    
     UsuarioCad micad= new UsuarioCad();
    private usuario dto;
private List<usuario> milista;
  
        public usuarioController() {
    try {
             listar("V");
       } catch (Exception e) {
       }
    }

    public usuario getDto() {
        return dto;
    }

    public void setDto(usuario dto) {
        this.dto = dto;
    }


    public List<usuario> getMilista() {
        return milista;
    }

    public void setMilista(List<usuario> milista) {
        this.milista = milista;
    }
 public void seleccionarfilas(SelectEvent event)throws Exception{
        try{
          dto= micad.consultaUsuario(((usuario)event.getObject()).getId());
        }
        catch(Exception e){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error al seleccionar el registro intente de nuevo"));

        }
                
    }
  public void listar(String ispst) throws Exception{
        
         try {
             if (ispst.equals("F")){
                 
             
             if (isPostBack()==false){
              
                    milista= micad.listarUsuarios();
             }
             }
             else{
                  milista= micad.listarUsuarios();
             }
         
         } catch (Exception e) {
            throw e;
        }
    }
     public boolean isPostBack(){
        boolean rs;
        rs=FacesContext.getCurrentInstance().isPostback();
        return rs;
    }
    public void seleccionar(usuario user)throws Exception{
        try{
        
          dto= micad.consultaUsuario(user.getId());
        }
        catch(Exception e){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error al seleccionar el registro intente de nuevo"));
        }        
    }
   public void inserta()throws Exception{
       
      
        try {
            micad.insertaUsuario(dto);
           this.listar("V");
                } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Aviso","El Cliente no existe, debe crear uno nuevo."));
             
            }
       
        }
    
       public void actualiza()throws Exception{
        try {
        
            micad.actualizaUsuario(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Aviso","Registro Actualizado Exitosamente."));
    
             this.listar("V");
    }
          catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error al Acualizar intente de nuevo"));

            throw e;
        }
        }

   
    public void busca() throws Exception{
        usuario miDto= new usuario();
        try {
       miDto= micad.consultaUsuario(dto.getId());
       if (miDto!=null){
        dto=miDto;
       }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Error al buscar el registro."));
       }     
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error!"));

            throw e;
        }
        }
  
    @PostConstruct
        public void init(){
            dto= new usuario();
        }
        //este es un cambio
    
}
