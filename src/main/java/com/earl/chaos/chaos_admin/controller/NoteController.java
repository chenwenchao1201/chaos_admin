package com.earl.chaos.chaos_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earl.chaos.chaos_admin.domain.Note;
import com.earl.chaos.chaos_admin.dto.ChallengeDto;
import com.earl.chaos.chaos_admin.dto.NoteDto;
import com.earl.chaos.chaos_admin.dto.ResultDto;
import com.earl.chaos.chaos_admin.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @PostMapping("/getNotes")
    public ResultDto getNotes(@RequestBody NoteDto noteDto) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", noteDto.getUserId());
        queryWrapper.eq("is_done", noteDto.getIsDone());
        queryWrapper.orderByDesc("level");
        return ResultDto.success("查询成功", noteService.list(queryWrapper));
    }

    @PostMapping("/getById")
    public ResultDto getById(@RequestBody ChallengeDto challengeDto) {
        Note note = noteService.getById(challengeDto.getId());
        if (note != null) {
            return ResultDto.success("查询成功", note);
        } else {
            return ResultDto.fail("查询失败，id不正确");
        }
    }

    // 添加
    @PostMapping("/add")
    public ResultDto add(@RequestBody Note note) {
        if (noteService.save(note)) {
            return ResultDto.successWithoutResult("添加成功");
        } else {
            return ResultDto.fail("添加失败");
        }
    }

    // 修改
    @PostMapping("/update")
    public ResultDto update(@RequestBody Note note) {
        if (noteService.updateById(note)) {
            return ResultDto.successWithoutResult("修改成功");
        } else {
            return ResultDto.fail("修改失败");
        }
    }

    // 删除
    @PostMapping("/delete")
    public ResultDto delete(@RequestBody Note note) {
        if (noteService.removeById(note)) {
            return ResultDto.successWithoutResult("删除成功");
        } else {
            return ResultDto.fail("删除失败");
        }
    }


}

