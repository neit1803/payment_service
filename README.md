# Bill Payment CLI

## Chức năng yêu cầu
- Add Fund
- CRUD Bill & search bill
- Pay Bill(s) at the same time with available fund, prioritize by early due_date any time
- Tracking due_date bills
- View History Payment Transactions
- Scheduled Bill payment, automatically do bill payment. Client could configure

## Giả định
- Hệ thống chỉ phục vụ một customer trong một lần chạy chương trình (Nếu nhiều cần phải xử lý nhiều trường hợp có thể xảy ra, race conditions)
- Không sử dụng DB, mọi dữ liệu trong phiên chạy sẽ bị xóa khi kết thúc chương trình
- Số tiền là số nguyên dương, không có phí giao dịch
- Khi thanh toán nhiều bill, hệ thống ưu tiên due date sớm hơn; nếu không đủ tiền cho toàn bộ danh sách thì không bill nào được thanh toán.
- Scheduled payment sẽ chỉ chạy khi ứng dụng chạy, không chạy ngầm ngay cả khi ứng dụng tắt. Để kiểm tra tự động thanh toán theo hạn, thay đổi ngày trên máy theo ngày thiết lập thanh toán của bill.
- Ngày tháng sử dụng một format thống nhất: `dd/MM/yyyy`.

## Usage
