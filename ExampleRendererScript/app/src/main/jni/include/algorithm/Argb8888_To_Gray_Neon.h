#ifndef __ARGB8888_GRAY_NEON_H__
#define __ARGB8888_GRAY_NEON_H__

#ifdef __cplusplus
extern "C" {
#endif


/// Grayscale 변환 함수 - NEON 명령어
void argb8888_to_gray_neon(void *in_pixels, void *out_pixels, int size);


#ifdef __cplusplus
}
#endif

#endif  /* __ARGB8888_GRAY_NEON_H__ */