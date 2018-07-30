package cn.ken.question.answering.system.controller.common;

import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.utils.RedisUtils;
import cn.ken.question.answering.system.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * author: shangkun <br/>
 * date: 2018/7/23 <br/>
 * what: 验证码 <br/>
 */
@RestController
@RequestMapping(value = "/api/common/validate")
@Api(tags="验证码")
public class ValidateCodeController extends Base {
    @Autowired
    private RedisUtils redisUtils;

    char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private int width = 67;
    private int height = 35;
    private int codeCount = 4;
    private int xx = 11;
    private int fontHeight = 18;
    private int codeY = 23;

    /**
     * 生成验证码
     * @return
     */
    @RequestMapping(value = "/code/generate", method = RequestMethod.GET)
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    public void getCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Pragma", "No-cache");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, fontHeight));
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 170; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        StringBuilder randomCode = new StringBuilder();
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i = 0; i < codeCount; i++) {
            String code = String.valueOf(codeSequence[random.nextInt(36)]);

            red = 20 + random.nextInt(110);
            green = 20 + random.nextInt(110);
            blue = 20 + random.nextInt(110);

            g.setColor(new Color(red, green, blue));
            g.drawString(code, (i + 1) * xx, codeY);

            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。
        HttpSession session = httpServletRequest.getSession();
        // 图象生效
        g.dispose();
        String sessionId = session.getId();
        setSession(randomCode.toString(),sessionId);

        ServletOutputStream sos = httpServletResponse.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.close();
    }

    /**
     * 多验证服务session共享
     * @param code
     * @param sessionId
     */
    public void setSession(String code,String sessionId){
        code = StringUtils.toLowerCaseLocal(code);
        //key为sessionId value为验证码 每次更新验证码 redis覆盖之前的验证码
        redisUtils.set(Constant.SESSION_ID + sessionId, code, Constant.TIMEOUT);
    }

    /**
     * 获取随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
