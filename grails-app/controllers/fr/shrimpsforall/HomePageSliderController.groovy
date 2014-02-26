package fr.shrimpsforall

import grails.converters.*;
import grails.plugin.springsecurity.annotation.Secured

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.awt.image.BufferedImage;

@Secured(['ROLE_ADMIN'])
class HomePageSliderController {

	def photoService

    def index() {
        [homePageSlider: HomePageSlider.get(1)]
    }

	def upload() {
		log.debug "upload params : ${params}"
		
		try {
			def photo = new PhotoHomePage()
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

			def homePageSlider = HomePageSlider.get(1)
			if (!homePageSlider) homePageSlider = new HomePageSlider(id: 1).save()
			homePageSlider.addToPhotos(photo)
			photo.homePageSlider = homePageSlider

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

	def save() {
		log.debug "save : $params"
		
		def photo = PhotoHomePage.get(params.id)
		photo.position = params.position as int
		photo.titre = params.titre
		photo.description = params.description

		if (!photo.save()) {
			photo.errors.each {
				println  it
			}
		}

		redirect action: "index"
	}

	def delete() {
		log.debug "delete : $params"
		def photo =PhotoHomePage.get(params.id)
		if (!photo.delete()) {
			photo.errors.each{log.error it}
		}
		redirect action: "index"
	}

}
