function generatePDF(){
    const element = document.getElementById("pdf");
    html2pdf(element).save();
}