package com.earl.chaos.chaos_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earl.chaos.chaos_admin.domain.Note;
import com.earl.chaos.chaos_admin.mapper.NoteMapper;
import com.earl.chaos.chaos_admin.service.NoteService;
import org.springframework.stereotype.Service;

/**
 * @author earl
 * @description 针对表【note】的数据库操作Service实现
 * @createDate 2022-02-23 22:02:01
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note>
        implements NoteService {

}




