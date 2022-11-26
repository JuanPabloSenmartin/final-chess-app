package validation;

import chess.game.GameMode;
import pieces.Pieces;
import validation.restrictions.Restriction;
import validation.restrictions.moveRestrictions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {
    public static Map<Pieces, MovementValidator> load(GameMode mode){
        switch (mode){
            case CAPABLANCA -> {
                return capablancaLoader();
            }
            case BERLIN -> {
                return berlinLoader();
            }
            default -> {
                return classicLoader();
            }
        }
    }
    private static Map<Pieces, MovementValidator> berlinLoader(){
        Map<Pieces, MovementValidator> map = new HashMap<>();

        //pawns
        //advance 1
        List<Restriction> pawnRestrictions1 = new ArrayList<>();
        pawnRestrictions1.add(new ForwardRestriction());
        pawnRestrictions1.add(new LimitMoveRestriction(1));
        pawnRestrictions1.add(new CaptureNotAllowedRestriction());
        pawnRestrictions1.add(new DiagonalMoveRestriction());
        MovementValidator pawnMv1 = new MovementValidator(pawnRestrictions1);

        //advance 2
        List<Restriction> pawnRestrictions2 = new ArrayList<>();
        pawnRestrictions2.add(new ForwardRestriction());
        pawnRestrictions2.add(new LimitMoveRestriction(2));
        pawnRestrictions2.add(new CaptureNotAllowedRestriction());
        pawnRestrictions2.add(new DiagonalMoveRestriction());
        pawnRestrictions2.add(new LimitAmountOfMovesRestriction(0));
        MovementValidator pawnMv2 = new MovementValidator(pawnRestrictions2);

        //eat
        List<Restriction> pawnRestrictions3 = new ArrayList<>();
        pawnRestrictions3.add(new ForwardRestriction());
        pawnRestrictions3.add(new LimitMoveRestriction(1));
        pawnRestrictions3.add(new SquareToNotEmptyRestriction());
        pawnRestrictions3.add(new VerticalMoveRestriction());
        pawnRestrictions3.add(new DifferentColorRestriction());
        MovementValidator pawnMv3 = new MovementValidator(pawnRestrictions3);
        MovementValidator pawnMovementValidator = new MovementValidator(List.of(pawnMv1, pawnMv2, pawnMv3));
        map.put(Pieces.PAWN, pawnMovementValidator);

        //rooks

        map.put(Pieces.ROOK, classicRookMovementValidator());

        //bishop

        map.put(Pieces.BISHOP, classicBishopMovementValidator());

        //queen

        map.put(Pieces.QUEEN, classicQueenMovementValidator());

        //horse

        map.put(Pieces.HORSE, classicHorseMovementValidator());

        //king

        map.put(Pieces.KING, classicKingMovementValidator());

        return map;
    }

    private static Map<Pieces, MovementValidator> capablancaLoader() {
        Map<Pieces, MovementValidator> map = classicLoader();

        List<Restriction> archbishopRestrictions1 = List.of(new HorseMoveRestriction(), new DifferentColorRestriction());
        MovementValidator archbishopMv1 = new MovementValidator(archbishopRestrictions1);
        List<Restriction> archbishopRestrictions2 = List.of(new DiagonalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator archbishopMv2 = new MovementValidator(archbishopRestrictions2);
        MovementValidator archbishopMovementValidator = new MovementValidator(List.of(archbishopMv1, archbishopMv2));
        map.put(Pieces.ARCHBISHOP, archbishopMovementValidator);

        List<Restriction> chancellorRestrictions1 = List.of(new HorseMoveRestriction(), new DifferentColorRestriction());
        MovementValidator chancellorMv1 = new MovementValidator(chancellorRestrictions1);
        List<Restriction> chancellorRestrictions2 = List.of(new HorizontalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator chancellorMv2 = new MovementValidator(chancellorRestrictions2);
        List<Restriction> chancellorRestrictions3 = List.of(new VerticalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator chancellorMv3 = new MovementValidator(chancellorRestrictions3);
        MovementValidator chancellorMovementValidator = new MovementValidator(List.of(chancellorMv1, chancellorMv2, chancellorMv3));
        map.put(Pieces.CHANCELLOR, chancellorMovementValidator);

        return map;
    }

    private static Map<Pieces, MovementValidator> classicLoader() {
        Map<Pieces, MovementValidator> map = new HashMap<>();

        //pawns

        map.put(Pieces.PAWN, classicPawnMovementValidator());

        //rooks

        map.put(Pieces.ROOK, classicRookMovementValidator());

        //bishop

        map.put(Pieces.BISHOP, classicBishopMovementValidator());

        //queen

        map.put(Pieces.QUEEN, classicQueenMovementValidator());

        //horse

        map.put(Pieces.HORSE, classicHorseMovementValidator());

        //king

        map.put(Pieces.KING, classicKingMovementValidator());

        return map;
    }
    private static MovementValidator classicPawnMovementValidator(){
        //advance 1
        List<Restriction> pawnRestrictions1 = new ArrayList<>();
        pawnRestrictions1.add(new ForwardRestriction());
        pawnRestrictions1.add(new LimitMoveRestriction(1));
        pawnRestrictions1.add(new CaptureNotAllowedRestriction());
        pawnRestrictions1.add(new VerticalMoveRestriction());
        MovementValidator pawnMv1 = new MovementValidator(pawnRestrictions1);

        //advance 2
        List<Restriction> pawnRestrictions2 = new ArrayList<>();
        pawnRestrictions2.add(new ForwardRestriction());
        pawnRestrictions2.add(new LimitMoveRestriction(2));
        pawnRestrictions2.add(new CaptureNotAllowedRestriction());
        pawnRestrictions2.add(new VerticalMoveRestriction());
        pawnRestrictions2.add(new LimitAmountOfMovesRestriction(0));
        MovementValidator pawnMv2 = new MovementValidator(pawnRestrictions2);

        //eat
        List<Restriction> pawnRestrictions3 = new ArrayList<>();
        pawnRestrictions3.add(new ForwardRestriction());
        pawnRestrictions3.add(new LimitMoveRestriction(1));
        pawnRestrictions3.add(new SquareToNotEmptyRestriction());
        pawnRestrictions3.add(new DiagonalMoveRestriction());
        pawnRestrictions3.add(new DifferentColorRestriction());
        MovementValidator pawnMv3 = new MovementValidator(pawnRestrictions3);

        return new MovementValidator(List.of(pawnMv1, pawnMv2, pawnMv3));
    }
    private static MovementValidator classicRookMovementValidator(){
        List<Restriction> rookRestrictions1 = List.of(new VerticalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator rookMv1 = new MovementValidator(rookRestrictions1);
        List<Restriction> rookRestrictions2 = List.of(new HorizontalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator rookMv2 = new MovementValidator(rookRestrictions2);

        return new MovementValidator(List.of(rookMv1, rookMv2));
    }
    private static MovementValidator classicBishopMovementValidator(){
        List<Restriction> bishopRestrictions1 = List.of(new DiagonalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator bishopMv1 = new MovementValidator(bishopRestrictions1);
        return new MovementValidator(List.of(bishopMv1));
    }
    private static MovementValidator classicQueenMovementValidator(){
        List<Restriction> queenRestrictions1 = List.of(new VerticalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator queenMv1 = new MovementValidator(queenRestrictions1);
        List<Restriction> queenRestrictions2 = List.of(new HorizontalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator queenMv2 = new MovementValidator(queenRestrictions2);
        List<Restriction> queenRestrictions3 = List.of(new DiagonalMoveRestriction(), new DifferentColorRestriction());
        MovementValidator queenMv3 = new MovementValidator(queenRestrictions3);
        return new MovementValidator(List.of(queenMv1, queenMv2, queenMv3));
    }
    private static MovementValidator classicHorseMovementValidator(){
        List<Restriction> horseRestrictions1 = List.of(new HorseMoveRestriction(), new DifferentColorRestriction());
        MovementValidator horseMv1 = new MovementValidator(horseRestrictions1);
        return new MovementValidator(List.of(horseMv1));
    }
    private static MovementValidator classicKingMovementValidator(){
        List<Restriction> kingRestrictions1 = List.of(new LimitMoveRestriction(1), new DifferentColorRestriction());
        MovementValidator kingMv1 = new MovementValidator(kingRestrictions1);
        List<Restriction> kingRestrictions2 = List.of(new CastleRestriction(), new HorizontalMoveRestriction(), new LimitMoveRestriction(2), new CaptureNotAllowedRestriction());
        MovementValidator kingMv2 = new MovementValidator(kingRestrictions2);
        return new MovementValidator(List.of(kingMv1, kingMv2));
    }
}
