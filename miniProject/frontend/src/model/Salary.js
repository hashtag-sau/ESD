import { getMonthName } from '../utils/dateUtil';

class Salary {
  constructor(data) {
    this.paymentDate = data.payment_date;
    this.takeHome = data.takeHome;
    this.grossPay = data.grossPay;
    this.details = data.details || [];
    this.deductionsDetails = data.deductionsDetails || [];
  }

  getMonthName() {
    return getMonthName(this.paymentDate);
  }

  getFinancialYear() {
    const date = new Date(this.paymentDate);
    const year = date.getFullYear();
    return date.getMonth() >= 3 ? `${year}-${year + 1}` : `${year - 1}-${year}`;
  }
}

export default Salary;
