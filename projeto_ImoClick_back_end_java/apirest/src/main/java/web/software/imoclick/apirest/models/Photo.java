package web.software.imoclick.apirest.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "photo")
public class Photo {
   @Id
   private String id;

   @DocumentReference(lazy=true) //faz referência ao imovel a qual a foto pertence
   private Imovel imovel;
   private String photo;

   @CreatedDate
   private LocalDateTime createdAt;
   @LastModifiedDate
   private LocalDateTime updatedAt;

    public Photo(){

    }
    public Photo(Imovel imovel, String photo){
        this.imovel = imovel;
        this.photo = photo;
    }


   public String getId() {
    return id;
   }
   public void setId(String id) {
    this.id = id;
   }
   public Imovel getImovel() {
    return imovel;
   }
   public void setImovel(Imovel imovel) {
    this.imovel = imovel;
   }
   public String getPhoto() {
    return photo;
   }
   public void setPhoto(String photo) {
    this.photo = photo;
   }
   public LocalDateTime getCreatedAt() {
    return createdAt;
   }
   public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
   }
   public LocalDateTime getUpdatedAt() {
    return updatedAt;
   }
   public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
   }


   

}
