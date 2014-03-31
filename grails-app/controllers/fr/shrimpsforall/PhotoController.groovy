package fr.shrimpsforall

import grails.converters.*;
import grails.plugin.springsecurity.annotation.Secured

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.awt.image.BufferedImage;

class PhotoController {

	def photoService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def produit() {
    	def photos = photoService.getPhotosProduit(params.id)
    	render template: 'photos', model: [photos: photos]
    }

    @Secured(['ROLE_ADMIN'])
	def upload() {
		log.debug "upload params : ${params}"
		
		try {
			def photo = new Photo()
			photo.data = request.getInputStream().getBytes()
			ByteArrayInputStream buff = new ByteArrayInputStream( photo.data )

			BufferedImage croppedImage = photoService.resize(buff, 350, 350)
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
			ImageIO.write(croppedImage, "jpg", baos);
			photo.data_small = baos.toByteArray();

			ByteArrayInputStream buff2 = new ByteArrayInputStream( photo.data )
			BufferedImage croppedImage2 = photoService.resize(buff2, 990, 415)
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream(1000);
			ImageIO.write(croppedImage2, "jpg", baos2);
			photo.data_slider = baos2.toByteArray();

			ByteArrayInputStream buff3 = new ByteArrayInputStream( photo.data )
			BufferedImage croppedImage3 = photoService.resize(buff3, 210, 132)
			ByteArrayOutputStream baos3 = new ByteArrayOutputStream(1000);
			ImageIO.write(croppedImage3, "jpg", baos3);
			photo.data_small_homepage = baos3.toByteArray();

			photo.produit = Produit.get(params["id"])
			photo.titre = request.getHeader('X-File-Name') as String
			
			if (photo.save()) {
				log.error "upload ok"
				
				//photoService.writePhoto( photo.id )
				
				log.debug "resize ok"
			} else {
				log.error photo.errors
			}

			return render(text: [success:true] as JSON, contentType:'text/json')
		} catch(Exception e) {
			log.error "upload ${e}"
			return render(text: [success:false] as JSON, contentType:'text/json')
		}
	}

	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def showPhoto() {
		//cache shared:true, neverExpires:true
		
		try {
			
			def photo = photoService.get( params.id )

			log.debug request.getHeader("If-Modified-Since")

			if(request.getHeader("If-Modified-Since"))
			{
				def reqDate = new Date(request.getHeader("If-Modified-Since"))
				if (photo.dateCreated < reqDate)
				{
					render(status: 304)
				}
			}
			else {
				switch (params.type)
				{
					case "full" : response.outputStream << photo.data; break;// write the image to the outputstream
					case "small" : response.outputStream << photo.data_small; break;
					case "slider" : response.outputStream << photo.data_slider; break;
					case "small_homepage" : response.outputStream << photo.data_small_homepage; break;
				}
				response.outputStream.flush()
			}
			
		} catch(Exception e) {
			log.error "showPhoto : ${e}"
		}
	}

	@Secured(['ROLE_ADMIN'])
	//@CacheEvict(value='photos', allEntries=true)
	//@CacheEvict(value='voitures', allEntries=true)
	def delete() {
		log.debug "delete Photo params : ${params}"
		
		try {
			def photo = Photo.get( params.id )
			photo.delete()
			log.debug "deletePhoto OK"
			render true
			
		} catch(Exception e) {
			log.error "deletePhoto : ${e}"
		}
	}
	
}
