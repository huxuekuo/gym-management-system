package com.milotnt.controller;

import com.milotnt.consts.ConstsEnum;
import com.milotnt.pojo.Member;
import com.milotnt.service.MemberService;
import com.milotnt.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //查询会员
    @RequestMapping(value = "/selMember" )
    public Object selectMember() {
        List<Member> memberList = memberService.findAll();
        return R.OK(memberList);
    }

    //新增会员
    @RequestMapping("/addMember")
    public Object addMember(@RequestBody Member member) {
        //会员账号&卡号随机生成
        Random random = new Random();
        String account1 = "2021";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        //初始密码
        String password = "123456";

        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        Integer nextClass = member.getCardClass();

        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);

        memberService.insertMember(member);

        return R.OK(true);
    }

    //删除会员
    @RequestMapping("/delMember")
    public Object deleteMember(@RequestParam Integer memberAccount) {
        memberService.deleteByMemberAccount(memberAccount);
        return R.OK(true);
    }

    //跳转会员修改页面
    @RequestMapping("/toUpdateMember")
    public Object toUpdateMember(@RequestParam Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        return R.OK(memberList);
    }

    //修改会员信息
    @RequestMapping("/updateMember")
    public Object updateMember(@RequestBody Member member) {
        memberService.updateMemberByMemberAccount(member);
        return R.OK(true);
    }

    //根据会员卡号查询
    @RequestMapping("/selByCard")
    public Object selectByCardId(@RequestParam Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        if (memberList != null) {
            return R.OK(memberList);
        }
        return R.Fail(ConstsEnum.MEMBER_NOT_EXIST);
    }

}
