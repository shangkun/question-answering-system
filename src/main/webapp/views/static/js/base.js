/**
 * Created by shangkun on 2018/7/13.
 */
var baseUrl = "./../../../api/";
var userUrl = baseUrl+"admin/user/";
var roleUrl = baseUrl+"admin/role/";
var sensitiveWordUrl = baseUrl+"config/sensitive/word/";
var lexiconUrl = baseUrl+"knowledge/lexicon/";
var knowledgeUrl = baseUrl+"knowledge/";
var configUrl = baseUrl+"config/";
var classificationUrl = baseUrl+"knowledge/classification/";
var greetingUrl = baseUrl+"knowledge/greeting/";
var qaUrl = baseUrl+"qa/";
var statisticsUrl = baseUrl+"statistics/";
var responseTypeStatisticsUrl = statisticsUrl+"response/type/";

var pId = "0";
var classificationArray = new Array();

var hour_start = " 00:00:00";
var hour_end = " 24:00:00";
var baseUrl2 = "./../../api/";
var serverInfoUrl = baseUrl2+"common/server/";
var userUrl2 = baseUrl2+"admin/user/";
var qaUrl2 = baseUrl2+"qa/";

var web_channel_id = 101;

var hasAnswerAndRecommendName = "有答案有推荐";
var hasAnswerName = "只有答案"
var hasRecommendName = "只有推荐"
var greetingName = "寒暄"
var unknownName = "未知"
var othersName = "其他";