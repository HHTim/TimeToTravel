/*如何使用請參考如下*/
/*在改呼叫的js加入這行，假如你要在admin_ann.js 就要在裡面加上下面這行來導入function
ex:import { getNotifyStatus } from './header.js'
*/
/*把引入的js 路徑前面加上type = module 定義為module，這樣才能使用該function
ex:
<script type="module" src="../js/admin_ann.js" defer></script>
*/

/*此為header js 自定義的function 測試用*/
export function getNotifyStatus() {
  console.log('getNotifyStatus');
  return true;
}
