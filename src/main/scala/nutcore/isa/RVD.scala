package nutcore.isa

import fpu.FPUOpType._
import fpu.FPUIOFunc._
import nutcore.{HasInstrType, HasNutCoreParameter}
import nutcore.FuType._
import nutcore.LSUOpType._
import Chisel.BitPat


object RVDInstr extends HasNutCoreParameter with HasInstrType {

  def FADD_D             = BitPat("b0000001??????????????????1010011")
  def FSUB_D             = BitPat("b0000101??????????????????1010011")
  def FMUL_D             = BitPat("b0001001??????????????????1010011")
  def FDIV_D             = BitPat("b0001101??????????????????1010011")
  def FSGNJ_D            = BitPat("b0010001??????????000?????1010011")
  def FSGNJN_D           = BitPat("b0010001??????????001?????1010011")
  def FSGNJX_D           = BitPat("b0010001??????????010?????1010011")
  def FMIN_D             = BitPat("b0010101??????????000?????1010011")
  def FMAX_D             = BitPat("b0010101??????????001?????1010011")
  def FCVT_S_D           = BitPat("b010000000001?????????????1010011")
  def FCVT_D_S           = BitPat("b010000100000?????????????1010011")
  def FSQRT_D            = BitPat("b010110100000?????????????1010011")
  def FLE_D              = BitPat("b1010001??????????000?????1010011")
  def FLT_D              = BitPat("b1010001??????????001?????1010011")
  def FEQ_D              = BitPat("b1010001??????????010?????1010011")
  def FCVT_W_D           = BitPat("b110000100000?????????????1010011")
  def FCVT_WU_D          = BitPat("b110000100001?????????????1010011")
  def FCVT_L_D           = BitPat("b110000100010?????????????1010011")
  def FCVT_LU_D          = BitPat("b110000100011?????????????1010011")
  def FMV_X_D            = BitPat("b111000100000?????000?????1010011")
  def FCLASS_D           = BitPat("b111000100000?????001?????1010011")
  def FCVT_D_W           = BitPat("b110100100000?????????????1010011")
  def FCVT_D_WU          = BitPat("b110100100001?????????????1010011")
  def FCVT_D_L           = BitPat("b110100100010?????????????1010011")
  def FCVT_D_LU          = BitPat("b110100100011?????????????1010011")
  def FMV_D_X            = BitPat("b111100100000?????000?????1010011")
  def FLD                = BitPat("b?????????????????011?????0000111")
  def FSD                = BitPat("b?????????????????011?????0100111")
  def FMADD_D            = BitPat("b?????01??????????????????1000011")
  def FMSUB_D            = BitPat("b?????01??????????????????1000111")
  def FNMSUB_D           = BitPat("b?????01??????????????????1001011")
  def FNMADD_D           = BitPat("b?????01??????????????????1001111")

  val fullTable = Array(
    FLD       -> List(InstrFI,   lsu, ld,      in_raw, out_raw),
    FSD       -> List(InstrFS,   lsu, sd,      in_raw, out_raw),

    FADD_D    -> List(InstrFR,   fpu, fadd,    in_raw, out_raw),
    FSUB_D    -> List(InstrFR,   fpu, fsub,    in_raw, out_raw),
    FMUL_D    -> List(InstrFR,   fpu, fmul,    in_raw, out_raw),
    FDIV_D    -> List(InstrFR,   fpu, fdiv,    in_raw, out_raw),
    FMIN_D    -> List(InstrFR,   fpu, fmin,    in_raw, out_raw),
    FMAX_D    -> List(InstrFR,   fpu, fmax,    in_raw, out_raw),
    FSGNJ_D   -> List(InstrFR,   fpu, fsgnj,   in_raw, out_raw),
    FSGNJN_D  -> List(InstrFR,   fpu, fsgnjn,  in_raw, out_raw),
    FSGNJX_D  -> List(InstrFR,   fpu, fsgnjx,  in_raw, out_raw),
    FSQRT_D   -> List(InstrFR,   fpu, fsqrt,   in_raw, out_raw),
    FMADD_D   -> List(InstrFR,   fpu, fmadd,   in_raw, out_raw),
    FNMADD_D  -> List(InstrFR,   fpu, fnmadd,  in_raw, out_raw),
    FMSUB_D   -> List(InstrFR,   fpu, fmsub,   in_raw, out_raw),
    FNMSUB_D  -> List(InstrFR,   fpu, fnmsub,  in_raw, out_raw),
    FCVT_S_D  -> List(InstrFR,   fpu, d2s,     in_raw,   out_box),
    FCVT_D_S  -> List(InstrFR,   fpu, s2d,     in_unbox, out_raw),
    // F -> G
    FCLASS_D  -> List(InstrFtoG, fpu, fclass,  in_raw, out_raw),
    FMV_X_D   -> List(InstrFtoG, fpu, fmv_f2i, in_raw, out_raw),
    FCVT_W_D  -> List(InstrFtoG, fpu, f2w,     in_raw, out_sext),
    FCVT_WU_D -> List(InstrFtoG, fpu, f2wu,    in_raw, out_sext),
    FCVT_L_D  -> List(InstrFtoG, fpu, f2l,     in_raw, out_raw),
    FCVT_LU_D -> List(InstrFtoG, fpu, f2lu,    in_raw, out_raw),
    FLE_D     -> List(InstrFtoG, fpu, fle,     in_raw, out_raw),
    FLT_D     -> List(InstrFtoG, fpu, flt,     in_raw, out_raw),
    FEQ_D     -> List(InstrFtoG, fpu, feq,     in_raw, out_raw),
    // G -> F
    FMV_D_X   -> List(InstrGtoF, fpu, fmv_i2f, in_raw, out_raw),
    FCVT_D_W  -> List(InstrGtoF, fpu, w2f,     in_raw, out_raw),
    FCVT_D_WU -> List(InstrGtoF, fpu, wu2f,    in_raw, out_raw),
    FCVT_D_L  -> List(InstrGtoF, fpu, l2f,     in_raw, out_raw),
    FCVT_D_LU -> List(InstrGtoF, fpu, lu2f,    in_raw, out_raw)
  )
  val table = fullTable.map(row =>
    row._1 -> row._2.take(3)
  )
  val ioFuncTable = fullTable.map(row =>
    row._1 -> row._2.drop(3)
  )
}