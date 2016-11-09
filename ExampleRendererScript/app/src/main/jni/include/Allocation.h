
#ifndef __ALLOCATION_H__
#define __ALLOCATION_H__

#ifdef cplusplus
extern "C" {
#endif

/*********************************************************
 * 메모리 할당 및 복사 관련 함수들
 *********************************************************/
void copy_to_in(const void *pixels, int size);  // in 메모리 할당 및 pixels 배열 복사 (size * 4)
void create_memory_to_out(int size);            // out 메모리 할당 (size * 4)
void copy_from_out(void *pixels, int size);     // out 메모리의 내용을 pixels 배열로 복사 (size * 4)

#ifdef cplusplus
};
#endif

#endif //__ALLOCATION_H__
