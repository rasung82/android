//
// Created by 우라성 on 2016. 11. 9..
//

#include <stdlib.h>
#include <stddef.h>
#include <string.h>
#include <stdbool.h>
#include "Allocation.h"


static void *g_in = NULL;   // in 메모리 영역
static void *g_out = NULL;  // out 메모리 영역

/*********************************************************
 * 메모리 얻는 함수들
 *********************************************************/

/**
 * In 메모리 포인터 반환
 */
void *get_in_memory()
{
    return g_in;
}


/**
 *  Out 메모리 포인터 반환
 */
void *get_out_memory()
{
    return g_out;
}


/*********************************************************
 * 메모리 할당 및 복사 관련 함수들
 *********************************************************/

/**
 *  In 메모리 할당 및 pixels 배열 복사 (size * 4)
 */
void copy_to_in(const void *pixels, int size)
{
    const int mem_size = size * 4;

    // 0. 유효성 검사
    if (pixels == NULL || size <= 0) {
        return;
    }

    // 1. 메모리가 이미 할당되어 있으면 해지
    if (g_in != NULL) {
        free(g_in);
    }

    // 2. 메모리 할당
    g_in = (void *)malloc(mem_size);

    // 3. 메모리 공간 복사
    memcpy(g_in, pixels, mem_size);
}

/**
 *  Out 메모리 할당 (size * 4)
 */
void create_memory_to_out(int size)
{
    const int mem_size = size * 4;

    // 0. 유효성 검사
    if (size <= 0) {
        return;
    }

    // 1. 메모리가 이미 할당되어 있으면 해지
    if (g_out != NULL) {
        free(g_out);
    }

    // 2. 메모리 할당
    g_out = (void *)malloc(mem_size);
}

/**
 *  Out 메모리의 내용을 pixels 배열로 복사 (size * 4)
 *
 */
void copy_from_out(void *pixels, int size)
{
    const int mem_size = size * 4;

    // 0. 유효성 검사
    if (g_out == NULL || pixels == NULL) {
        return;
    }

    // 1. 메모리 복사
    memcpy(pixels, g_out, mem_size);
}


