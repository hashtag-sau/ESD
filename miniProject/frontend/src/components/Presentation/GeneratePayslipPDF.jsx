import { jsPDF } from 'jspdf'; // Import jsPDF
import { getMonthName } from '../../utils/dateUtil';

const GeneratePayslipPDF = ({ selectedMonth }) => {
  console.log({ selectedMonth });

  // Function to generate PDF
  const generatePayslipPDF = () => {
    const doc = new jsPDF();

    // Set custom font if needed (Optional: for specific fonts)
    doc.setFont('helvetica'); // Default font (you can change to any other font supported by jsPDF)

    // Title
    doc.setFontSize(18);
    doc.text(
      'Payslip for ' +
        getMonthName(selectedMonth.paymentDate) +
        ' ' +
        new Date(selectedMonth.paymentDate).getFullYear(),
      20,
      20
    );

    // Take Home & Gross Pay
    doc.setFontSize(14);
    doc.text('Take Home: ' + selectedMonth.takeHome, 20, 30);
    doc.text('Gross Pay: ' + selectedMonth.grossPay, 20, 40);

    // Details Table
    doc.setFontSize(12);
    doc.text('Details:', 20, 50);
    const details = selectedMonth.details;
    let y = 60;
    details.forEach((detail) => {
      doc.text(detail.name + ': ' + detail.amount, 20, y);
      y += 10;
    });

    // Deductions Table
    doc.text('Deductions:', 20, y + 10);
    y += 20;
    const deductions = selectedMonth.deductionsDetails;
    deductions.forEach((deduction) => {
      doc.text(deduction.name + ': ' + deduction.amount, 20, y);
      y += 10;
    });

    // Save the PDF
    doc.save('Payslip_' + selectedMonth.month + '.pdf');
  };

  return (
    <div className="mt-6 flex justify-end">
      <button onClick={generatePayslipPDF} className="bg-blue-600 text-white p-2 rounded-md">
        Download Payslip as PDF
      </button>
    </div>
  );
};

export default GeneratePayslipPDF;
