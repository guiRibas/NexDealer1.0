package Model;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class MakePdf {

	private String resultOfSave = "";
	
	public String getResultOfSave() {
		return resultOfSave;
	}
	public void setResultOfSave(String resultOfSave) {
		this.resultOfSave = resultOfSave;
	}

	public String newPdf(Client client, Vehicle vehicle){

		String toWay = "";
		String cpf = client.getCpf();
		cpf = cpf.replaceAll("[^0-9]", "");
		
		String firstName[] = client.getName().split(" ");
		
		Document document = new Document();
		
		try {
			//PEGANDO UMA INSTACIA DE PDF WRITER
			toWay = "/home/cabrito/workspace/NexDealer/lib/generatedPdf/"+firstName[0]+"-"+cpf+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(toWay));
			
			//ABRINDO O DOCUMENTO
			document.open();
			
			//SETANDO O TAMANHO DA PAGINA E O BACKGROUND
			document.setPageSize(PageSize.A4);
			PdfContentByte canvas = writer.getDirectContentUnder();
	        Image image = Image.getInstance("/home/cabrito/workspace/NexDealer/lib/img/ficha.jpg");
	        image.scaleAbsolute(PageSize.A4);
	        image.setAbsolutePosition(0, 0);
	        canvas.addImage(image);
			
	        PdfContentByte cb = writer.getDirectContent();
	        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	        cb.saveState();
	        cb.setFontAndSize(bf, 10);
	        
	        cb.beginText();
	        cb.moveText(40, 718);
	        cb.showText(client.getName());
	        cb.endText();
	        
	        cb.beginText();
	        cb.moveText(360, 718);
	        cb.showText(client.getCpf());
	        cb.endText();
	        
	        cb.beginText();
	        cb.moveText(490, 718);
	        cb.showText(client.getRg());
	        cb.endText();
	       
	        cb.beginText();
	        switch (client.getGender()) {
			case "M":
				cb.moveText(185, 694);
		        cb.showText("X");
				break;
			case "F":
				cb.moveText(205, 694);
		        cb.showText("X");
		        break;
			default:
				break;
			}
	        cb.endText();
	        
//	        cb.beginText();
//	        cb.moveText(280, 718);
//	        cb.showText(client.getBirthDate());
//	        cb.endText();
//	        
//	        cb.beginText();
//	        cb.moveText(280, 718);
//	        cb.showText(client.getCivilStatus());
//	        cb.endText();
//	        
//	        cb.beginText();
//	        cb.moveText(280, 718);
//	        cb.showText(String.valueOf(client.getContact().getDdd()));
//	        cb.endText();
//	        
//	        cb.beginText();
//	        cb.moveText(280, 718);
//	        cb.showText(String.valueOf(client.getContact().getTelephone()));
//	        cb.endText();
//	        
//	        cb.beginText();
//	        cb.moveText(280, 718);
//	        cb.showText(client.getContact().getEmail());
//	        cb.endText();
	        
	        //CONFIGURANDO DADOS DO VEÍCULO
	        cb.beginText();
	        cb.moveText(158, 103);
	        cb.showText(vehicle.getVersion().getModel().getBrand().getDescription());
	        cb.endText();
	        
	        cb.beginText();
	        cb.moveText(335, 103);
	        cb.showText(vehicle.getVersion().getModel().getDescription()+"/ "+vehicle.getVersion().getDescription());
	        cb.endText();
	        
	        cb.restoreState();
			resultOfSave = "Sucesso! PDF gerado";
		} catch (DocumentException dE) {
			resultOfSave = "Erro. Problema ao salvar o arquivo";
		} catch (IOException iOE){
			resultOfSave = "Erro. Problema ao gerar o arquivo";
		} finally {
			document.close();
		}
		return toWay;
	}
	
}
