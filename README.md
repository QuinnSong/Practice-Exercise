## Practice-Exercise

### End to end flow

An end to end flow automated testing using keyword driven framework covering below steps:

    1. Login to the demo application Online Store
    2. Selecting a product category from Top Menu
    3. Selecting a product and adding it to the cart
    4. Go to payment details page and complete the order
    5. Verify details from final Confirmation page

### Environment
* 	Apache POI 3.15
* 	Selenium Java client driver 3.3.1

### Website
* [This awesome website](http://www.store.demoqa.com) is designed for automated testing


### Known issue

After purchase button on payment page is clicked, the confirmation page will see the following exception:

```Fatal error: Uncaught exception 'phpmailerException' with message 'Invalid address: (setFrom) ' in /home1/lsharm/public_html/demoqa.com/store/wp-includes/class-phpmailer.php:1023 Stack trace: #0 /home1/lsharm/public_html/demoqa.com/store/wp-includes/pluggable.php(352): PHPMailer->setFrom('', 'ToolsQA Online ...', false) #1 /home1/lsharm/public_html/demoqa.com/store/wp-content/plugins/wp-e-commerce/wpsc-includes/purchase-log-notification.class.php(286): wp_mail('ccf-midi@hotmai...', 'Order Pending: ...', '<p>Thank you, y...', 'From: "ToolsQA ...', Array) #2 /home1/lsharm/public_html/demoqa.com/store/wp-content/plugins/wp-e-commerce/wpsc-includes/purchase-log.helpers.php(275): WPSC_Purchase_Log_Notification->send() #3 /home1/lsharm/public_html/demoqa.com/store/wp-content/plugins/wp-e-commerce/wpsc-includes/purchase-log.helpers.php(185): wpsc_send_customer_email(Object(WPSC_Purchase_Log)) #4 [internal function]: _wpsc_action_update_purchase_log_status('11900', 2, '1', Object(WPSC_Purchase_Log)) #5 /home1/lsharm/public_html/ in /home1/lsharm/public_html/demoqa.com/store/wp-includes/class-phpmailer.php on line 1023```


### Author

 * Quinn Song


### Credit to:
* [ToolsQA.com](http://toolsqa.com)

### 2017.04.04