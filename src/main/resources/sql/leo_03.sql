-- CREATE DATABASE IF NOT EXISTS `ggtest`;

-- use `ggtest`;


-- DROP TABLE IF EXISTS `user`;
-- CREATE TABLE `user` (
--   `USER_ID` int NOT NULL AUTO_INCREMENT COMMENT '會員編號',
--   `USER_ACCOUNT` varchar(50) NOT NULL COMMENT '會員帳號',
--   `USER_PASSWORD` varchar(128) NOT NULL COMMENT '會員密碼',
--   `USER_NAME` varchar(50) NOT NULL COMMENT '會員姓名',
--   `USER_PHONE` varchar(50) NOT NULL COMMENT '會員電話',
--   `USER_NICKNAME` varchar(50) DEFAULT NULL COMMENT '會員暱稱',
--   `USER_AVATAR` longblob COMMENT '會員頭像',
--   -- `USER_GENDER` bit(1) NOT NULL COMMENT '會員性別',
--   `USER_GENDER` tinyint(1) NOT NULL COMMENT '會員性別',
--   `USER_BIRTHDATE` date NOT NULL COMMENT '會員生日',
--   `USER_SIGN_DATETIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '註冊日期',
--   `USER_EMAIL` varchar(50) NOT NULL COMMENT '會員信箱',
--   -- `USER_STATUS` bit(1) NOT NULL COMMENT '會員狀態',
--   `USER_STATUS` tinyint(1) NOT NULL COMMENT '會員狀態',
--   `USER_NEWS_STATUS` tinyint(1) DEFAULT NULL COMMENT '會員訊息狀態',
--   PRIMARY KEY (`USER_ID`)
-- ) COMMENT='使用者資料表';
-- INSERT INTO `user` (`USER_ID`,`USER_ACCOUNT`,`USER_PASSWORD`,`USER_NAME`,`USER_PHONE`,`USER_NICKNAME`,`USER_AVATAR`,`USER_GENDER`,`USER_BIRTHDATE`,`USER_SIGN_DATETIME`,`USER_EMAIL`,`USER_STATUS`,`USER_NEWS_STATUS`) VALUES (1,'ABC123','sss','Peter','0911111111','Peter King','','0','1888-01-01','2023-05-23 00:32:06','peter001@gmail.com','0',0);
-- INSERT INTO `user` (`USER_ID`,`USER_ACCOUNT`,`USER_PASSWORD`,`USER_NAME`,`USER_PHONE`,`USER_NICKNAME`,`USER_AVATAR`,`USER_GENDER`,`USER_BIRTHDATE`,`USER_SIGN_DATETIME`,`USER_EMAIL`,`USER_STATUS`,`USER_NEWS_STATUS`) VALUES (2,'ASD123','sss','Windy','0922222222','Peter wife','','0','1888-01-01','2023-05-23 00:32:06','windy001@gmail.com','0',0);
-- INSERT INTO `user` (`USER_ID`,`USER_ACCOUNT`,`USER_PASSWORD`,`USER_NAME`,`USER_PHONE`,`USER_NICKNAME`,`USER_AVATAR`,`USER_GENDER`,`USER_BIRTHDATE`,`USER_SIGN_DATETIME`,`USER_EMAIL`,`USER_STATUS`,`USER_NEWS_STATUS`) VALUES (3,'QWE123','sss','Leo','0933333333','Peter dog','','0','1888-01-01','2023-05-23 00:32:06','leo001@gmail.com','0',0);
-- INSERT INTO `user` (`USER_ID`,`USER_ACCOUNT`,`USER_PASSWORD`,`USER_NAME`,`USER_PHONE`,`USER_NICKNAME`,`USER_AVATAR`,`USER_GENDER`,`USER_BIRTHDATE`,`USER_SIGN_DATETIME`,`USER_EMAIL`,`USER_STATUS`,`USER_NEWS_STATUS`) VALUES (4,'eeeeeeee','sss','Enigma','0921860927','','','0','2023-05-10','2023-06-07 18:42:17','leochiou0927@gmail.com','1',0);

-- =================================================== 
-- ===================================================
-- ===================================================


DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `POST_ID` int NOT NULL AUTO_INCREMENT COMMENT '文章編號',
  `USER_ID` int DEFAULT NULL COMMENT '會員編號',
  `POST_TITLE` varchar(50) NOT NULL COMMENT '文章標題',
  `POST_CONTENT` varchar(10000) NOT NULL COMMENT '文章內容',
  `POST_DATE` datetime NOT NULL DEFAULT (now()) COMMENT '發表時間',
  `LIKES` int DEFAULT '0' COMMENT '按讚數',
  `POST_PHOTO` longblob COMMENT '文章圖片',
  `POST_TYPE_ID` tinyint NOT NULL COMMENT '文章類型編號',
  `POST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '上次修改時間',
  `COMMENTS` int DEFAULT '0' COMMENT '留言數',
  PRIMARY KEY (`POST_ID`)
)  COMMENT='部落格';

INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (1,1,'【台南景點推薦】2023台南一日遊這樣玩！最夯台南旅遊好玩景點全攻略','<p><span style=\"color: #ff0000;\"><strong><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://pic.pimg.tw/anrine910070/1601456732-371322748-g.jpg\" alt=\"台南超人氣旅遊景點&amp;一日遊路線全攻略\" width=\"524\" height=\"349\" /></strong></span></p>\n<p>&nbsp;</p>\n<p><span style=\"color: #ff0000;\"><strong>台南景點推薦2023</strong></span>完成！<span style=\"color: #ff0000;\"><strong>台南一日遊行程、台南旅遊景點</strong></span>如何規劃？<span style=\"color: #ff0000;\"><strong>台南私房景點、台南好玩的地方</strong></span>又有哪些？<br />看這篇超強台南景點攻略就對了，最夯的台南必去景點＆台南好去處全收錄，看完就不用再問台南哪裡好玩啦～<br /><br />台南是國內旅遊重鎮，台南市區景點<span style=\"color: #3366ff;\"><strong>赤崁樓、安平老街、四草綠色隧道、神農街、奇美博物館</strong>&hellip;</span>都經典到不行；<br />郊區<span style=\"color: #3366ff;\"><strong>七股鹽山、北門鹽田、白河關子嶺、水道博物館</strong></span>等也超精彩，快跟著波比的10條台南一日遊景點推薦出發吧！</p>\n<p style=\"text-align: left;\"><strong>台南景點攻略目錄</strong></p>\n<nav>\n<ul>\n<li style=\"text-align: left;\"><a href=\"https://bobby.tw/blog/post/229896600#1\" target=\"_self\" rel=\"nofollow ugc noreferrer noopener\">台南景點地圖：台南市區＆郊區景點推薦</a></li>\n<li style=\"text-align: left;\"><a href=\"https://bobby.tw/blog/post/229896600#3\" target=\"_self\" rel=\"nofollow ugc noreferrer noopener\">台南景點介紹＆台南好去處大集合</a></li>\n<li style=\"text-align: left;\"><a href=\"https://bobby.tw/blog/post/229896600#4\" target=\"_self\" rel=\"nofollow ugc noreferrer noopener\">台南一日遊行程推薦</a></li>\n<li style=\"text-align: left;\"><a href=\"https://bobby.tw/blog/post/229896600#5\" target=\"_self\" rel=\"nofollow ugc noreferrer noopener\">台南旅遊票券熱門推薦</a></li>\n</ul>\n</nav>\n<p><span style=\"background-color: #ff0000; color: #ffffff;\">▼<strong>台南旅遊重點攻略</strong>▼</span></p>\n<p><a href=\"https://anrine910070.pixnet.net/blog/post/229896600\" target=\"_blank\" rel=\"noopener\">【台南景點】一日遊規劃＆好玩景點地圖</a></p>\n<p><span style=\"background-color: #ff0000; color: #ffffff;\">▼<strong>台南住宿懶人包</strong>▼</span></p>\n<p><a href=\"https://anrine910070.pixnet.net/blog/post/229774124\" target=\"_blank\" rel=\"noopener\">【台南住宿】15間最夯飯店清單</a><br /><a href=\"https://anrine910070.pixnet.net/blog/post/230582464\" target=\"_blank\" rel=\"noopener\">【台南親子飯店】11間歡樂住宿精選</a><br /><a href=\"https://anrine910070.pixnet.net/blog/post/229933650\" target=\"_blank\" rel=\"noopener\">【關子嶺溫泉】7間熱門泡湯會館</a></p>\n<p><span style=\"background-color: #ff0000; color: #ffffff;\">▼<strong>台南熱門票券&nbsp;(比官網更便宜)</strong>▼</span></p>\n<p>1.&nbsp;<a href=\"https://www.klook.com/zh-TW/activity/8875-chimei-museum-permanent-exhibition-ticket-tainan/?aid=417\" target=\"_blank\" rel=\"nofollow ugc noreferrer noopener\">奇美博物館門票</a><br />2.&nbsp;<a href=\"https://www.klook.com/zh-TW/activity/27309-ten-drum-cultural-village-ticket-tainan/?aid=417\" target=\"_blank\" rel=\"nofollow ugc noreferrer noopener\">十鼓仁糖文創園區門票</a><br />3.&nbsp;<a href=\"https://www.klook.com/zh-TW/activity/44015-tainan-city-zuojhen-fossil-park/?aid=417\" target=\"_blank\" rel=\"nofollow ugc noreferrer noopener\">左鎮化石園區門票</a><br />4.&nbsp;<a href=\"https://www.kkday.com/zh-tw/product/101730?cid=2290\" target=\"_blank\" rel=\"nofollow ugc noreferrer noopener\">臺南市美術館門票</a></p>\n<p>&nbsp;</p>\n<p><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://pic.pimg.tw/anrine910070/1646044072-2424036880-g.jpg\" alt=\"郊區景電地圖\" width=\"335\" height=\"335\" /></p>\n<p>&nbsp;</p>','2023-05-22 20:42:59',1,null,5,'2023-06-05 19:17:52',8);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (2,1,'第一次來高雄, 請教一下這樣的行程會很辛苦嗎?','<div id=\"td_post_12124010\">\n<div>\n<div id=\"post_message_12124010\" class=\"vb_postbit\">Day 1 》1820 到達高雄機場 &rarr; 住宿check in &rarr;&nbsp;<span style=\"color: #a0522d;\">自強夜市</span>(<span style=\"color: #15181e;\">晚餐)&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span>酒吧/夜店看看<span style=\"color: #15181e;\">&nbsp;&rarr; 洗洗睡</span><br /><br /><span style=\"color: #15181e;\">Day 2 》&nbsp;</span><span style=\"color: #2e8b57;\">橋頭糖廠文化園區&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">午飯(</span><span style=\"color: #2e8b57;\">待定</span><span style=\"color: #000000;\">)</span><span style=\"color: #15181e;\">&nbsp;&rarr;&nbsp;</span><span style=\"color: #15181e;\">左營古城&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span>孔子廟&nbsp;<span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">龍虎塔(1730關)</span>&nbsp;<span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">蓮池潭</span><span style=\"color: #15181e;\">&nbsp;&rarr; 新堀江商圈(路經看看美麗島捷運站) &rarr;&nbsp;</span><span style=\"color: #15181e;\">晚餐</span><span style=\"color: #15181e;\">(</span><span style=\"color: #000000;\">三牛牛肉麵/劉家酸菜白肉鍋)</span><span style=\"color: #15181e;\">&nbsp;&rarr;&nbsp;</span><span style=\"color: #ff8c00;\">待定</span><span style=\"color: #15181e;\">&nbsp;&rarr; 洗洗睡</span><br /><br />Day 3 》舊打狗驛故事館&nbsp;<span style=\"color: #15181e;\">&rarr;&nbsp;</span>高雄武德殿&nbsp;<span style=\"color: #15181e;\">&rarr;&nbsp;</span>棧貳庫KW2&nbsp;<span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">找地方</span><span style=\"color: #15181e;\">午飯(</span><span style=\"color: #a0522d;\">駁二附近的古早味</span><span style=\"color: #15181e;\">)</span>&nbsp;<span style=\"color: #15181e;\">&rarr;</span>&nbsp;駁二藝術特區(C6-7:伊藤潤二快閃店) + 大義倉庫&nbsp;<span style=\"color: #15181e;\">&rarr;</span>&nbsp;轉轉音樂大港橋&nbsp;<span style=\"color: #15181e;\">&rarr;</span>&nbsp;高雄流行音樂中心&nbsp;<span style=\"color: #15181e;\">&rarr;</span>&nbsp;貢多拉船<span style=\"color: #15181e;\">&nbsp;&rarr;&nbsp;</span><span style=\"color: #a0522d;\">瑞豐夜市</span><span style=\"color: #15181e;\">(晚餐)</span><span style=\"color: #15181e;\">&nbsp;&rarr;&nbsp;</span><span style=\"color: #15181e;\">洗洗睡</span><br /><br />午飯和<span style=\"color: #15181e;\">晚餐各位有沒有推介? 行程又有沒有什麼可以增減?&nbsp;</span>謝謝 😙</div>\n</div>\n</div>','2023-05-22 20:42:59',0,NULL,1,'2023-06-05 11:52:38',3);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (3,2,'台北-宜蘭自駕建議','<p><span style=\"color: #15181e;\">請教一下星期日的時間由<span style=\"font-size: 24px;\"><em><strong>台北自駕到宜蘭的話會塞車嗎？難度大嗎？</strong></em></span></span><br />正計劃7月的星期日由台北出發到宜蘭玩2日1夜回台北，2大2小(最細3歲)，已訂滑梯民宿，但位置關係所以打算租車，只是在考慮直接租車由台北出發還是乘客運到宜蘭羅東再租車哪個會比較好⋯<br />之前只試過由高雄小港機場自駕到懇丁，但已是3年前的事了⋯<br />求建議m(_ _)m</p>','2023-05-22 20:42:59',0,NULL,1,'2023-06-05 11:52:52',2);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (4,1,'請教一下台東行程安排','<p>已經選了一些想去的景點, 可惜多良車站因收費亭及周邊設施修繕工程所以去不了<br /><br /><span style=\"color: #15181e;\">Day 1&nbsp;</span><span style=\"color: #15181e;\">》</span><span style=\"color: #15181e;\">0930-</span><span style=\"color: #15181e;\">1132</span><span style=\"color: #15181e;\">從高雄坐 自強3000 371次 到</span><span style=\"color: #15181e;\">台東</span>&nbsp;<span style=\"color: #15181e;\">&rarr; 找地方午飯&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">台東阿伯小白屋&nbsp;</span><span style=\"color: #15181e;\">&rarr;</span><span style=\"color: #15181e;\">&nbsp;國際地標(海濱公園)&nbsp;</span><span style=\"color: #15181e;\">&rarr;</span>&nbsp;<span style=\"color: #15181e;\">生日蛋糕公園+台東美術館 (沒展覽想看, 到此一遊)&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">臺東森林公園&nbsp;</span><span style=\"color: #15181e;\">&rarr;</span><span style=\"color: #15181e;\">&nbsp;小野柳&nbsp;</span><span style=\"color: #15181e;\">&rarr;</span><span style=\"color: #15181e;\">&nbsp;加路蘭遊憩區&nbsp;</span><span style=\"color: #15181e;\">&rarr;</span>&nbsp;<span style=\"color: #15181e;\">晚上回&nbsp;</span><span style=\"color: #15181e;\">台東舊鐵道路廊&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">鐵花村音樂聚落&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">台東觀光夜市 (晚餐)</span><br /><span style=\"color: #15181e;\">Day 2&nbsp;</span><span style=\"color: #15181e;\">》</span><span style=\"color: #15181e;\">池上飯包文化故事館(</span><span style=\"color: #15181e;\">午餐</span><span style=\"color: #15181e;\">:</span><span style=\"color: #15181e;\">池上飯包)&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">伯朗大道&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #a0522d;\">晚上好像沒什麼好玩?🤔</span><br /><span style=\"color: #15181e;\">Day&nbsp;</span><span style=\"color: #15181e;\">3&nbsp;</span><span style=\"color: #15181e;\">》</span><span style=\"color: #15181e;\">初鹿牧場&nbsp;</span><span style=\"color: #15181e;\">&rarr;&nbsp;</span><span style=\"color: #15181e;\">布農部落(午餐可選:風味餐)(需1400前到看表演)&nbsp;</span>&rarr;&nbsp;<span style=\"color: #15181e;\">原生應用植物園(</span><span style=\"color: #15181e;\">午餐可選:</span><span style=\"color: #15181e;\">火鍋吃到飽)&nbsp;</span>&rarr;&nbsp;<span style=\"color: #a0522d;\">晚上好像沒什麼好玩?🤔</span><br /><br /><span style=\"color: #15181e;\">請問</span><span style=\"color: #15181e;\">各位有沒有推介的</span><span style=\"color: #15181e;\">午飯和</span><span style=\"color: #15181e;\">晚餐? 行程有沒有什麼建議?&nbsp;</span><span style=\"color: #15181e;\">謝謝 😙</span></p>','2023-05-22 20:42:59',0,NULL,1,'2023-06-05 11:54:06',1);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (5,3,'請問台東五天四夜住宿問題','<p>想請問各位旅遊達人<br />我預計9/23-9/27前往台東旅遊<br />去回搭火車，中間三天租車<br />因為有小孩同行，行程打算走慢活路線<br />目前預計四個晚上都住南豐鐵花棧<br />但開始規劃行程後<br />覺得是不是應該住池上兩晚比較好<br />這樣才能一天走海線，再繞到台東關山，池上，兩天玩山線後回到台東市區<br /><br />可以請大家給我些意見嗎</p>\n<div id=\"gtx-trans\" style=\"position: absolute; left: -35px; top: -14px;\">&nbsp;</div>','2023-05-22 20:42:59',1,NULL,1,'2023-06-05 11:54:17',4);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (6,1,'請幫我看花東五天四夜熱氣球行程','<p>七月九日至七月十三日要到花東五天四夜，房間都訂好了，開車兩大兩小（四歲及六歲），熱氣球跟瑞穗天合飯店內遊玩是必備行程，懇請版友賜教，行程如下：<br /><br />7/9（四）<br />台北出發<br />迴瀾星巴克貨櫃屋<br />台開心農場<br />東大門夜市<br />花蓮市住宿<br />7/10（五）<br />遠雄海洋公園<br />鐵花村<br />台東民宿住宿<br />7/11（六）<br />多良車站<br />櫻木花道平交道<br />鹿野高台熱氣球 光雕音樂會<br />台東民宿住宿<br /><br />因為我們只是想看一下熱氣球沒有要坐 所以是不是下午到就好了？上網查 最佳觀賞時間下午是五點至七點 請教今天的行程會太緊，來不及嗎？<br /><br /><br />7/12（日）<br />伯朗大道<br />（大池豆皮店。關山米國學校）<br />池上197公路<br />瑞穗天合飯店內遊樂區遊玩<br />瑞穗住宿<br />7/13（一）<br />佳興冰菓室<br />台泥DAKA園區<br /><br />請教有什麼有注意或改進的地方，非常謝謝大家。</p>','2023-05-22 20:42:59',0,NULL,1,'2023-06-05 11:53:51',0);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (7,2,'台灣東部有哪些一定要去的景點啊?','<p>疫情過後想去東部玩大概一個禮拜，想說先規劃之後可以說走就走，如果可以的話希望提供一些特殊的景點，最好是海景的 感謝~~</p>','2023-05-22 20:42:59',0,NULL,1,'2023-06-05 11:53:34',2);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (8,1,'台東旅遊心得感想','<p>距離上次去台東 (應該是2006.08)&nbsp; 已經1年半了吧</p>\n<p>上次去是去找極惡小賊的朋友 住在鹿野 帶著我們去遊山玩水</p>\n<p>鹿野高台、龍田國小、初鹿牧場&nbsp;、原生園植物園、六十石山(花蓮)、私房景點玩水</p>\n<p>走的是台9線</p>\n<p>這次我們的規畫是以台11線為主 還要住在台東市一天 吃吃美食</p>\n<p>找民宿是件很累得事情 台東的民宿很多 有的圖片會跟實體差很多吧</p>\n<p>但還好 這次住的都比網站上看的好很多 哈哈 還好我眼光好&nbsp;呵呵~~</p>\n<p>當然有機會的話還是要去住這次捨棄的&nbsp;&nbsp;<a href=\"http://www.flying.com.tw/sunny.htm\">光宿</a>&nbsp;及位於都蘭的&nbsp;&nbsp;<a href=\"http://wind.e089.com.tw/wind6.htm\">來吹涼風</a>&nbsp;</p>\n<p>當住宿地點決定了 行程就很好規畫了 拿嚕台東地圖看嚕看</p>\n<p>我想去的三仙台&nbsp; 風車教堂&nbsp; 其他地方倒是沒啥感覺 那就沿途在看看吧 隨性一點玩吧</p>\n<p>前往台東 真得好遠喔~~ 但一切也是很值得的拉 辛苦一下下得到的 卻是一個讓你難忘的旅行</p>\n<p>很可惜的是花蓮真得好遠喔&nbsp; 不然也很想接著到花蓮去玩 這次還是以台東為主吧</p>\n<p>還好 這次的規畫相當正確 時間的掌握 路況指標清楚 帶著愉快輕鬆的心情最重要</p>\n<p>沿途看到很多熱血的台灣人 騎著單車或是重機 不畏 烈陽&nbsp; 寒風 下雨</p>\n<p>體力好ㄉ就 單車環島 體力差的可以選擇將車運到台東 騎車慢慢欣賞沿途風光</p>\n<p>看了 也會有一點點的心動 但想到昂貴鐵馬 再加上我3分鐘熱度 還是算了吧 自動放棄</p>\n<p>在這一塊土地上 感覺到大家的熱情&nbsp;&nbsp;純真&nbsp; 不像在都市中&nbsp; 大家戰戰兢兢的生活 忙碌的腳步</p>\n<p>這 你可以慢慢 就連這裡的狗 都是慵懶的在路上 看到車來了 還心不甘情不願的離開</p>\n<p>不用飛到國外就可以享受到的風光及悠哉的心情</p>\n<p>當地的民俗風情 是不是跟在西部的我們大大的不同</p>\n<p>在台灣 還沒去過的還很多</p>\n<p>還沒發掘到的也很多 都需要我們用心的去感受</p>\n<p>希望有時間可以再多多前往台灣更多美麗的地方</p>\n<p>拍下相片 寫下紀錄 推薦給大家 讓更多人看到台灣的美</p>','2023-05-22 20:42:59',0,NULL,3,'2023-06-05 11:53:22',0);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (9,1,'阿森測試9','內容是這樣子的，9','2023-05-22 20:42:59',1,NULL,6,'2023-06-05 11:53:09',1);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (13,1,'阿森測試www','<p>dddddd</p>','2023-06-04 14:51:12',0,NULL,1,'2023-06-04 14:52:34',0);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (15,1,'麻煩大家請教一下2天一夜宜蘭礁溪行程安排','<div id=\"td_post_12125074\">\n<div>\n<div id=\"post_message_12125074\" class=\"vb_postbit\">6月底預計<strong>品文旅 礁溪</strong>悠哉過生活--享受飯店設施<br />那是否可詢問附近景點安排呢-走不傷荷包景點為主<br />第1天可能會中午之前抵達礁溪--到15:00在入住<br />這中間可以去哪逛逛呢---之後還有哪些地方可以去看看<br />第2天11點退房--之後是否還有地點可以去走<br />因為要回高雄可能也不能待太晚<br />請教一下有經驗的大大唷<br />感恩大家</div>\n</div>\n</div>','2023-06-05 17:24:23',1,NULL,1,'2023-06-07 23:30:50',2);
INSERT INTO `blog` (`POST_ID`,`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_DATE`,`LIKES`,`POST_PHOTO`,`POST_TYPE_ID`,`POST_UPDATE_TIME`,`COMMENTS`) VALUES (16,1,'有人想去環島的嗎？','<div id=\"td_post_12126699\">\n<div>\n<div id=\"post_message_12126699\" class=\"vb_postbit\">有沒有人想去環島的，徒步、單車、機車都可以，有人有興趣嗎</div>\n</div>\n</div>\n<div>&nbsp;</div>','2023-06-05 17:24:44',0,NULL,1,'2023-06-07 23:28:21',2);

-- select b.* from BLOG b where 1=1
-- and b.POST_TITLE like '%'
-- and b.POST_TYPE_ID IN(6,5)
-- and b.POST_ID IN( SELECT POST_ID
--     FROM ARTICLE_TAG
--     WHERE TAG_ID IN (1, 2)
--     );
-- SELECT B.* 
-- FROM BLOG B 
-- INNER JOIN ARTICLE_TAG T1 ON B.POST_ID = T1.POST_ID AND T1.TAG_ID = 1 
-- INNER JOIN ARTICLE_TAG T2 ON B.POST_ID = T2.POST_ID AND T2.TAG_ID = 2;
-- SELECT B.*FROM BLOG B
-- WHERE B.POST_ID IN (  SELECT POST_ID FROM ARTICLE_TAG WHERE TAG_ID = 1
-- ) AND B.POST_ID IN (  SELECT POST_ID FROM ARTICLE_TAG WHERE TAG_ID = 2);
-- SELECT B.*FROM BLOG B
-- WHERE EXISTS (  SELECT 1 FROM ARTICLE_TAG WHERE POST_ID = B.POST_ID AND TAG_ID = 1
-- ) AND EXISTS (  SELECT 1 FROM ARTICLE_TAG WHERE POST_ID = B.POST_ID AND TAG_ID = 2);


DROP TABLE IF EXISTS  `article_comment`;
CREATE TABLE `article_comment` (
  `COMMENT_NO` int NOT NULL AUTO_INCREMENT COMMENT '留言編號',
  `POST_ID` int NOT NULL COMMENT '文章編號',
  `USER_ID` int NOT NULL COMMENT '會員編號',
  `COMMENT_CONTENT` varchar(500) NOT NULL COMMENT '留言內容',
  `COMMENT_DATETIME` datetime Not null DEFAULT (now()) COMMENT '留言時間',
  PRIMARY KEY (`COMMENT_NO`)
) COMMENT='文章留言';

INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (15,1,3,'好文 讚喔','2023-05-26 14:34:51');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (18,1,2,'太感謝了 正想規劃台南旅行','2023-05-26 15:39:14');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (19,1,2,'好文，精闢','2023-05-26 20:48:21');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (20,1,2,'感謝分享~~','2023-05-26 20:50:02');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (21,2,2,'市區的捷運、公車、公共腳踏車(U bike)很方便，你想去的這些地方距離都不遠，我認為這些行程可以。','2023-05-30 21:44:23');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (22,9,1,'qqwwwww','2023-05-30 21:44:59');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (24,1,3,'求一個 台東版攻略','2023-06-02 19:21:00');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (25,3,1,'周日往宜蘭估計0800~11:00 應該還是會稍塞 其他時間應該就還好 注意安全有耐心 不會難開的','2023-06-04 00:55:08');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (26,3,1,'的確是有塞車的可能.如果擔心塞車可以考慮搭火車 到宜蘭再搭計程車就可以了.','2023-06-04 00:55:51');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (27,2,3,'第一交通不是很方便，第二裡面根本沒什麼動物，你可以先上網查一下官網介紹就可以有個想法了','2023-06-04 00:59:44');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (28,4,3,'2022年底， 在此用餐，很推.....  部落炭烤 950台東縣台東市新生路3號','2023-06-04 01:06:12');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (29,5,1,'南豐鐵花棧到池上開車一個小時,覺得沒有差到什麼','2023-06-04 01:12:41');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (30,5,1,'因為池上晚什麼都沒有,要看個人喜好吧,有要去南橫嗎?有的話覺得住一個晚上就可以了吧','2023-06-04 01:12:43');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (31,5,1,'您好，請問你的台東之行已圓滿完成了嗎，是否可以分享一下！XD','2023-06-04 01:14:13');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (32,7,1,'花蓮：我會選擇去太魯閣國家公園，住一天。並去走一趟錐鹿古道。','2023-06-04 01:28:28');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (33,7,1,'台東：我會去池上騎腳踏車，及 去台東森林公園。','2023-06-04 01:28:31');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (36,16,2,'你有預計日期嗎?','2023-06-06 10:10:15');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (39,1,4,'+1 求台東攻略~','2023-06-06 22:09:43');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (40,1,4,'有人想糾團嗎?','2023-06-06 23:03:10');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (41,15,3,'離飯店比較近的是礁溪溫泉公園，有足湯，有一些幾米的畫作雕像可','2023-06-07 01:41:42');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (42,1,1,'感謝支持~~','2023-06-07 23:16:13');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (43,5,4,'同上 可否分享一下~','2023-06-07 23:21:48');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (44,16,1,'有想騎機車環島，走南橫','2023-06-07 23:29:00');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (45,15,3,'以拍照 比較遠一點的是礁溪街上的湯圍溝溫泉公園，不過有點年久失修的破落感','2023-06-07 23:31:38');
INSERT INTO `article_comment` (`COMMENT_NO`,`POST_ID`,`USER_ID`,`COMMENT_CONTENT`,`COMMENT_DATETIME`) VALUES (46,2,1,'OK我在GOOGLE看看~ 感謝解答','2023-06-07 23:56:58');


DROP TABLE IF EXISTS  `article_type`;
CREATE TABLE `article_type` (
  `POST_TYPE_ID` tinyint NOT NULL AUTO_INCREMENT COMMENT '文章類型編號',
  `POST_TYPE` varchar(50) NOT NULL COMMENT '文章類型',
  PRIMARY KEY (`POST_TYPE_ID`)
)  COMMENT='文章類型';

INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (1,'問題');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (2,'情報');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (3,'心得');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (4,'討論');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (5,'攻略');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (6,'其他');


DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `TAG_ID` int NOT NULL AUTO_INCREMENT COMMENT '標籤編號',
  `TAG` varchar(30) NOT NULL COMMENT '標籤',
  PRIMARY KEY (`TAG_ID`)
)  COMMENT='標籤種類';

INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (1,'相撲');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (2,'傳奇');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (3,'大相撲');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (7,'問題');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (8,'eee');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (9,'台南');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (10,'赤崁樓');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (11,'七股鹽山');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (12,'一日遊');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (13,'高雄');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (14,'宜蘭');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (15,'自駕');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (16,'塞車');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (17,'台東');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (18,'台東美術館');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (19,'住宿');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (20,'花東');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (21,'花蓮');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (22,'熱氣球');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (23,'東部');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (24,'疫情');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (25,'海景');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (26,'台11線');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (27,'三仙台');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (28,'奇美博物館');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (29,'單車');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (30,'環島');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (31,'糾團');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (32,'礁溪');


DROP TABLE IF EXISTS  `article_tag`;
CREATE TABLE `article_tag` (
  `ARTICLE_TAG_NO` int NOT NULL AUTO_INCREMENT COMMENT '文章標籤編號',
  `POST_ID` int NOT NULL COMMENT '文章編號',
  `TAG_ID` int NOT NULL COMMENT '標籤編號',
  PRIMARY KEY (`ARTICLE_TAG_NO`)
) COMMENT='文章標籤';

INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (10,1,9);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (11,1,10);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (12,1,11);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (13,1,12);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (20,2,13);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (21,3,14);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (22,3,15);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (23,3,16);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (24,4,17);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (25,4,18);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (26,5,17);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (27,5,19);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (28,6,20);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (29,6,17);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (30,6,21);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (31,6,22);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (32,7,23);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (33,7,24);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (34,7,25);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (35,7,21);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (36,7,17);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (37,8,17);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (38,8,26);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (39,8,27);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (40,13,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (44,2,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (45,3,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (46,9,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (48,7,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (49,6,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (50,4,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (51,5,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (52,15,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (53,16,7);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (54,16,29);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (55,16,30);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (56,16,31);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (57,15,32);
INSERT INTO `article_tag` (`ARTICLE_TAG_NO`,`POST_ID`,`TAG_ID`) VALUES (58,15,14);


DROP TABLE IF EXISTS `press_like`;
CREATE TABLE `press_like` (
  `LIKE_NO` int NOT NULL AUTO_INCREMENT COMMENT '按讚流水號',
  `USER_ID` int NOT NULL COMMENT '會員編號',
  `POST_ID` int NOT NULL COMMENT '文章編號',
  PRIMARY KEY (`LIKE_NO`)
)  COMMENT='按讚';

INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (8,2,1);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (9,3,1);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (11,2,3);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (22,1,3);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (23,1,2);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (24,1,8);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (27,1,13);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (37,1,16);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (38,1,15);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (39,1,5);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (41,1,1);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (43,1,9);



DROP TABLE IF EXISTS `favorite_article`;
CREATE TABLE `favorite_article` (
  `FAVORITE_ARTICLE` int NOT NULL AUTO_INCREMENT COMMENT '文章收藏編號',
  `USER_ID` int DEFAULT NULL COMMENT '會員編號',
  `POST_ID` int NOT NULL COMMENT '文章編號',
  PRIMARY KEY (`FAVORITE_ARTICLE`)
)  COMMENT='文章收藏';

INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (9,1,4);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (10,1,5);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (11,1,6);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (13,1,8);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (16,1,1);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (18,1,7);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (19,1,2);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (21,1,3);


DROP VIEW IF EXISTS `default_blog`;   
CREATE VIEW default_blog AS
SELECT 
    b.POST_ID AS POST_ID, 
    b.USER_ID AS USER_ID, 
    b.POST_TITLE AS POST_TITLE, 
    b.POST_CONTENT AS POST_CONTENT, 
    b.POST_DATE AS POST_DATE, 
    b.LIKES AS LIKES, 
    b.POST_PHOTO AS POST_PHOTO, 
    b.POST_TYPE_ID AS POST_TYPE_ID, 
    b.POST_UPDATE_TIME AS POST_UPDATE_TIME, 
    b.COMMENTS AS COMMENTS,
    ac.USER_ID AS LAST_COMMENT_USER_ID, 
    ac.COMMENT_CONTENT AS LAST_COMMENT, 
    ac.COMMENT_DATETIME AS LAST_COMMENT_DATETIME, 
    at.POST_TYPE AS LAST_POST_TYPE,
    u.USER_NAME AS USER_NAME,
    u2.USER_NAME AS LAST_COMMENT_USER_NAME
FROM 
    blog b 
    LEFT JOIN (
        SELECT 
            c.* 
        FROM 
            article_comment c 
            INNER JOIN (
                SELECT 
                    POST_ID, 
                    MAX(COMMENT_DATETIME) AS MAX_COMMENT_DATETIME
                FROM 
                    article_comment
                GROUP BY 
                    POST_ID
            ) max_c ON c.POST_ID = max_c.POST_ID AND c.COMMENT_DATETIME = max_c.MAX_COMMENT_DATETIME
    ) ac ON b.POST_ID = ac.POST_ID
    LEFT JOIN article_type at ON b.POST_TYPE_ID = at.POST_TYPE_ID
    LEFT JOIN `user` u ON b.USER_ID = u.USER_ID
    LEFT JOIN `user` u2 ON ac.USER_ID = u2.USER_ID;
DROP VIEW IF EXISTS `view_article_comment`;    
CREATE VIEW view_article_comment AS
SELECT 
    ac.COMMENT_NO,
    ac.POST_ID,
    ac.USER_ID,
    ac.COMMENT_CONTENT,
    ac.COMMENT_DATETIME,
    u.USER_NAME,
    u.USER_AVATAR
FROM 
    timetotravel.article_comment ac
    JOIN `user` u ON ac.USER_ID = u.USER_ID;

    
-- RENAME TABLE old_table_name TO new_table_name;
-- RENAME TABLE COMMENT TO ARTICLE_COMMENT;
-- RENAME TABLE TAG TO TAGS;
-- RENAME TABLE ARTICLE_COLLECTION TO FAVORITE_ARTICLE;
-- RENAME TABLE ARTICLE_TAG_TYPE TO ARTICLE_TYPE;
-- ALTER TABLE BLOG ADD COLUMN POST_UPDATE_TIME DATETIME COMMENT '上次修改時間';
-- 加上FK 名稱 重新調整!!! 目前先沒有 加進去
-- ALTER TABLE `BLOG` ADD CONSTRAINT `BLOG_FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `MEMBER`(`USER_ID`);
-- ALTER TABLE `BLOG` ADD CONSTRAINT `BLOG_FK_POST_TYPE_ID` FOREIGN KEY (`POST_TYPE_ID`) REFERENCES `ARTICLE_TYPE`(`POST_TYPE_ID`);

-- ALTER TABLE `ARTICLE_COMMENT` ADD CONSTRAINT `ARTICLE_COMMENT_FK_POST_ID` FOREIGN KEY (`POST_ID`) REFERENCES `BLOG`(`POST_ID`);
-- ALTER TABLE `ARTICLE_COMMENT` ADD CONSTRAINT `ARTICLE_COMMENT_FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `MEMBER`(`USER_ID`);

-- ALTER TABLE `ARTICLE_TAG` ADD CONSTRAINT `ARTIVLE_TAG_FK_POST_ID` FOREIGN KEY (`POST_ID`) REFERENCES `BLOG`(`POST_ID`);
-- ALTER TABLE `ARTICLE_TAG` ADD CONSTRAINT `ARTIVLE_TAG_FK_TAG_ID` FOREIGN KEY (`TAG_ID`) REFERENCES `TAGS`(`TAG_ID`);

-- ALTER TABLE `PRESS_LIKE` ADD CONSTRAINT `PRESS_LIKE_FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `MEMBER`(`USER_ID`);
-- ALTER TABLE `PRESS_LIKE` ADD CONSTRAINT `PRESS_LIKE_FK_POST_ID` FOREIGN KEY (`POST_ID`) REFERENCES `BLOG`(`POST_ID`);

-- ALTER TABLE `FAVORITE_ARTICLE` ADD CONSTRAINT `FAVORITE_ARTICLE_FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `MEMBER`(`USER_ID`);
-- ALTER TABLE `FAVORITE_ARTICLE` ADD CONSTRAINT `FAVORITE_ARTICLE_FK_POST_ID` FOREIGN KEY (`POST_ID`) REFERENCES `BLOG`(`POST_ID`);
/*-- ++++++++++++++++++ 移除外鍵
ALTER TABLE BLOG DROP FOREIGN KEY BLOG_FK_USER_ID;
ALTER TABLE BLOG DROP FOREIGN KEY BLOG_FK_POST_TYPE_ID;
ALTER TABLE ARTICLE_COMMENT DROP FOREIGN KEY ARTICLE_COMMENT_FK_POST_ID;
ALTER TABLE ARTICLE_COMMENT DROP FOREIGN KEY ARTICLE_COMMENT_FK_USER_ID;
ALTER TABLE ARTICLE_TAG DROP FOREIGN KEY ARTIVLE_TAG_FK_POST_ID;
ALTER TABLE ARTICLE_TAG DROP FOREIGN KEY ARTIVLE_TAG_FK_TAG_ID;
ALTER TABLE PRESS_LIKE DROP FOREIGN KEY PRESS_LIKE_FK_USER_ID;
ALTER TABLE PRESS_LIKE DROP FOREIGN KEY PRESS_LIKE_FK_POST_ID;
ALTER TABLE FAVORITE_ARTICLE DROP FOREIGN KEY FAVORITE_ARTICLE_FK_USER_ID;
ALTER TABLE FAVORITE_ARTICLE DROP FOREIGN KEY FAVORITE_ARTICLE_FK_POST_ID;
*/-- ++++++++++++++++++ 移除外鍵

