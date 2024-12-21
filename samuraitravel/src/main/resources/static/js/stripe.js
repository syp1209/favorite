const stripe = Stripe('pk_test_51QJTrqRwOLXWwlUb1YwNOq7VTLeE7B0JPXNwoRutM5WlFomDpRuxm48GhP0zjqTo5plC2RceXaYCIog4GRqRrq4J004Bqvcumr');
 const paymentButton = document.querySelector('#paymentButton');
 
 paymentButton.addEventListener('click', () => {
   stripe.redirectToCheckout({
     sessionId: sessionId
   })
 });