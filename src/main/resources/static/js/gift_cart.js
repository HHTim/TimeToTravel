import { getCurrentUserInformation } from './header.js';

$('.btn-test-jquery').on('click', function () {
  alert('this is a test');
  getCurrentUserInformation();
});
